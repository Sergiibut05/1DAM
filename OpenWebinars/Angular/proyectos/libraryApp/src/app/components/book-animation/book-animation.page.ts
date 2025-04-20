import {
  Component,
  AfterViewInit,
  ElementRef,
  ViewChild
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  IonButton
} from '@ionic/angular/standalone';

import * as THREE from 'three';
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader.js';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-book-animation',
  templateUrl: './book-animation.page.html',
  styleUrls: ['./book-animation.page.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, IonButton]
})
export class BookAnimationPage implements AfterViewInit {
  @ViewChild('rendererContainer', { static: false }) rendererContainer!: ElementRef;

  private renderer!: THREE.WebGLRenderer;
  private scene!: THREE.Scene;
  private camera!: THREE.PerspectiveCamera;
  private clock = new THREE.Clock();
  private mixer!: THREE.AnimationMixer;
  private model!: THREE.Group;

  private backgroundMaterial!: THREE.ShaderMaterial;
  private backgroundMesh!: THREE.Mesh;
  private particles!: THREE.Points;

  private targetRotation = { x: 0, y: 0 };
  private currentRotation = { x: 0, y: 0 };
  private isDragging = false;
  private previousMousePosition = { x: 0, y: 0 };

  constructor(private router: Router, private auth: AuthService) {}

  ngAfterViewInit(): void {
    this.initThree();
    this.animate();
    window.addEventListener('resize', this.onWindowResize);

    const canvas = this.renderer.domElement;
    canvas.addEventListener('mousedown', this.onPointerDown);
    canvas.addEventListener('mousemove', this.onPointerMove);
    canvas.addEventListener('mouseup', this.onPointerUp);
    canvas.addEventListener('mouseleave', this.onPointerUp);
    canvas.addEventListener('touchstart', this.onTouchStart);
    canvas.addEventListener('touchmove', this.onTouchMove);
    canvas.addEventListener('touchend', this.onPointerUp);
  }

  private initThree(): void {
    const container = this.rendererContainer.nativeElement;
    const width = container.clientWidth;
    const height = container.clientHeight;

    this.scene = new THREE.Scene();
    this.camera = new THREE.PerspectiveCamera(20, width / height, 0.1, 1000);
    this.camera.position.set(0, 0.5, 2.2);
    this.camera.lookAt(0, 0.15, 0);

    this.renderer = new THREE.WebGLRenderer({ antialias: true, alpha: true });
    this.renderer.setSize(width, height);
    this.renderer.shadowMap.enabled = true;
    this.renderer.shadowMap.type = THREE.PCFSoftShadowMap;
    container.appendChild(this.renderer.domElement);

    const ambientLight = new THREE.AmbientLight(0xffffff, 0.4);
    this.scene.add(ambientLight);

    const dirLight = new THREE.DirectionalLight(0xffffff, 0.8);
    dirLight.position.set(0.1, 2, 0);
    dirLight.castShadow = true;
    dirLight.shadow.mapSize.width = 1024;
    dirLight.shadow.mapSize.height = 1024;
    dirLight.shadow.camera.near = 0.5;
    dirLight.shadow.camera.far = 20;
    this.scene.add(dirLight);

    const shadowPlane = new THREE.Mesh(
      new THREE.PlaneGeometry(5, 5),
      new THREE.ShadowMaterial({ opacity: 0.3 })
    );
    shadowPlane.rotation.x = -Math.PI / 2;
    shadowPlane.position.y = -0.1;
    shadowPlane.receiveShadow = true;
    this.scene.add(shadowPlane);

    const bgGeometry = new THREE.PlaneGeometry(100, 100);
    this.backgroundMaterial = new THREE.ShaderMaterial({
      uniforms: {
        u_time: { value: 0.0 }
      },
      vertexShader: `
        varying vec2 vUv;
        void main() {
          vUv = uv;
          gl_Position = projectionMatrix * modelViewMatrix * vec4(position, 1.0);
        }
      `,
      fragmentShader: `
        uniform float u_time;
        varying vec2 vUv;

        void main() {
          vec2 uv = vUv - 0.5;
          float dist = length(uv) * 1.5;
          float gradient = smoothstep(1.0, 0.0, dist);

          vec3 centerColor = vec3(0.1, 0.1, 0.2);
          vec3 edgeColor = vec3(0.02, 0.02, 0.05);
          vec3 color = mix(edgeColor, centerColor, gradient);

          gl_FragColor = vec4(color, 1.0);
        }
      `,
      depthWrite: false,
      transparent: true
    });
    this.backgroundMesh = new THREE.Mesh(bgGeometry, this.backgroundMaterial);
    this.backgroundMesh.position.z = -50;
    this.scene.add(this.backgroundMesh);

    const particlesGeometry = new THREE.BufferGeometry();
    const particleCount = 1000;
    const positions = [];

    for (let i = 0; i < particleCount; i++) {
      positions.push(
        (Math.random() - 0.5) * 100,
        (Math.random() - 0.5) * 100,
        -50 - Math.random() * 100
      );
    }

    particlesGeometry.setAttribute('position', new THREE.Float32BufferAttribute(positions, 3));
    const particlesMaterial = new THREE.PointsMaterial({
      color: 0xffffff,
      size: 0.4,
      opacity: 0.3,
      transparent: true
    });
    this.particles = new THREE.Points(particlesGeometry, particlesMaterial);
    this.scene.add(this.particles);

    const loader = new GLTFLoader();
    loader.load('../../../assets/3dBook.glb', (gltf) => {
      this.model = gltf.scene;
      this.model.traverse((child) => {
        if ((child as THREE.Mesh).isMesh) {
          child.castShadow = true;
        }
      });
      
      // Baja el libro (ajusta el valor de y)
      this.model.position.y = 0.005;  // Mueve el libro hacia abajo

      this.scene.add(this.model);

      if (gltf.animations.length) {
        this.mixer = new THREE.AnimationMixer(this.model);
        gltf.animations.forEach((clip) => {
          this.mixer.clipAction(clip).play();
        });
      }
    });
  }

  private animate = (): void => {
    requestAnimationFrame(this.animate);

    const delta = this.clock.getDelta();
    if (this.mixer) this.mixer.update(delta);

    if (this.backgroundMaterial) {
      this.backgroundMaterial.uniforms['u_time'].value += delta;
    }

    const positions = this.particles.geometry.attributes['position'].array as Float32Array;
    for (let i = 0; i < positions.length; i += 3) {
      positions[i + 2] += 0.05;
      if (positions[i + 2] > 0) positions[i + 2] = -100;
    }
    this.particles.geometry.attributes['position'].needsUpdate = true;

    if (this.model) {
      this.currentRotation.x += (this.targetRotation.x - this.currentRotation.x) * 0.1;
      this.currentRotation.y += (this.targetRotation.y - this.currentRotation.y) * 0.1;
      this.model.rotation.x = this.currentRotation.x;
      this.model.rotation.y = this.currentRotation.y;
    }

    this.renderer.render(this.scene, this.camera);
  };

  private onPointerDown = (event: MouseEvent) => {
    this.isDragging = true;
    this.previousMousePosition.x = event.clientX;
    this.previousMousePosition.y = event.clientY;
  };

  private onPointerMove = (event: MouseEvent) => {
    if (!this.isDragging) return;

    const deltaX = event.clientX - this.previousMousePosition.x;
    const deltaY = event.clientY - this.previousMousePosition.y;

    this.targetRotation.y += deltaX * 0.005;
    this.targetRotation.x += deltaY * 0.005;

    this.previousMousePosition.x = event.clientX;
    this.previousMousePosition.y = event.clientY;
  };

  private onTouchStart = (event: TouchEvent) => {
    if (event.touches.length === 1) {
      this.isDragging = true;
      this.previousMousePosition.x = event.touches[0].clientX;
      this.previousMousePosition.y = event.touches[0].clientY;
    }
  };

  private onTouchMove = (event: TouchEvent) => {
    if (!this.isDragging || event.touches.length !== 1) return;

    const deltaX = event.touches[0].clientX - this.previousMousePosition.x;
    const deltaY = event.touches[0].clientY - this.previousMousePosition.y;

    this.targetRotation.y += deltaX * 0.005;
    this.targetRotation.x += deltaY * 0.005;

    this.previousMousePosition.x = event.touches[0].clientX;
    this.previousMousePosition.y = event.touches[0].clientY;
  };

  private onPointerUp = () => {
    this.isDragging = false;
  };

  private onWindowResize = (): void => {
    const container = this.rendererContainer.nativeElement;
    const width = container.clientWidth;
    const height = container.clientHeight;

    this.camera.aspect = width / height;
    this.camera.updateProjectionMatrix();
    this.renderer.setSize(width, height);
  };

  onClick() {
    if(this.auth.user$){
      this.router.navigate(['/products']);
    }else{
      this.router.navigate(['/registration']);
    }
    
  }
}
