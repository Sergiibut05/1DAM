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

  constructor(private fb: FormBuilder, 
    proveService: ProveService,
    private router: Router
  ) {
    this.router.events.subscribe(() => {
      this.isHomeView = this.router.url === '/home'; // Verifica si la ruta actual es '/home'
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
