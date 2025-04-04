import { Component, inject } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { RouterModule } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { error } from 'console';

@Component({
  selector: 'app-initial',
  imports: [RouterOutlet, RouterModule, ReactiveFormsModule, CommonModule],
  templateUrl: './initial.component.html',
  styleUrl: './initial.component.scss'
})
export class InitialComponent {
  
  public formEntrada: FormGroup;
  public formLogin: FormGroup;
  public isLoggedIn = false;
  public isHomeView: boolean = false;
  public backroundImg = 'https://images.unsplash.com/photo-1513185041617-8ab03f83d6c5?q=80&w=3870&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D';
  public isHomeColor = '';
  public authService = inject(AuthService);
  public registerOption = true;
  public backroundColor = 'white';
  public dropdownOpen = false;
  protected userData: any;

  constructor(private fb: FormBuilder, 
    private router: Router
  ) {

    this.router.events.subscribe(() => {
      this.isHomeView = this.router.url === '/home';
      this.isHomeColor = this.isHomeView ? 'text-white' : 'text-black';
    });

    
    this.formEntrada = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.pattern('[a-zA-Z0-9]*')]],
      confirmPassword: ['', [Validators.required, this.samePassword.bind(this)]]

    });

    this.formLogin = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.pattern('[a-zA-Z0-9]*')]]
    })
  }
  

  onClickRegister(){
    this.registerOption = true;
  }

  onClickLogin(){
    this.registerOption = false;
  }

  ngOnInit(): void {
    this.authService.user$.subscribe((user) => {
      if (user) {
        this.authService.currentUserSig.set({
          email: user.email!,
          username: user.displayName!,
        });
        this.isLoggedIn = true;
        this.userData = user;
      } else {
        this.authService.currentUserSig.set(null);
      }
    });
  }

  public samePassword(control: AbstractControl): ValidationErrors | null {
    const password = control.parent?.get('password')?.value;
    const confirmPassword = control.value;

    return password === confirmPassword ? null: {notSame: true}
  }

  onSubmitRegister(): void {
    const rawForm = this.formEntrada.getRawValue();
    this.authService
      .register(rawForm.email, rawForm.username, rawForm.password)
      .subscribe(() => {
        this.router.navigateByUrl('/');
      })
  }

  errorMessage: string | null = null;

  onSubmitLogin(): void {
    const rawForm = this.formLogin.getRawValue();
    this.authService
      .login(rawForm.email, rawForm.password)
      .subscribe({
        next: () => {
        this.router.navigateByUrl('/');
      },
      error: (err) => {
        this.errorMessage = err.code;
      },
      });
  }

  onSubmitLogout(): void {
    this.authService.logout();
    this.isLoggedIn = false;
  }

  scrollToLogin(): void {
    this.router.navigate([`/home`])
    if(this.router.url === '/home') {
    const loginElement = document.getElementById('login');
    if (loginElement) {
        loginElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }}
  }
  scrollToAccount(): void {
    this.router.navigate([`/home`])
    if(this.router.url === '/home') {
    const loginElement = document.getElementById('Account');
    if (loginElement) {
        loginElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }}
  }
  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

}
