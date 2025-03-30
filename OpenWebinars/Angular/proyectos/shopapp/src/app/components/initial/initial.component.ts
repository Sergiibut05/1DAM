import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ProveService } from '../../services/sesion.service';

@Component({
  selector: 'app-initial',
  imports: [RouterOutlet, RouterModule, ReactiveFormsModule, CommonModule],
  templateUrl: './initial.component.html',
  styleUrl: './initial.component.scss'
})
export class InitialComponent {
  public formEntrada: FormGroup;
  public isLoggedIn = false;
  private proveService: ProveService;
  public isHomeView: boolean = false;
  public backroundImg = 'https://images.unsplash.com/photo-1513185041617-8ab03f83d6c5?q=80&w=3870&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D';
  public isHomeColor = '';


  constructor(private fb: FormBuilder, 
    proveService: ProveService,
    private router: Router
  ) {

    this.router.events.subscribe(() => {
      this.isHomeView = this.router.url === '/home';
      this.isHomeColor = this.isHomeView ? 'text-white' : 'text-black';
    });

    this.proveService = proveService;
    
    this.formEntrada = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.pattern('[a-zA-Z0-9]*')]]

    });
  }
  public editarEntrada(): void {
    this.proveService.setUserData(this.formEntrada.value);
    this.isLoggedIn = true;
  }
}
