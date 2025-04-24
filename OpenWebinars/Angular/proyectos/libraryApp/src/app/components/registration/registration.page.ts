import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AbstractControl, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar, IonList, IonInput, IonItem, IonInputPasswordToggle, IonButtons, IonMenuButton, IonLabel, IonNote, IonButton, IonIcon } from '@ionic/angular/standalone';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.page.html',
  styleUrls: ['./registration.page.scss'],
  standalone: true,
  imports: [ReactiveFormsModule ,IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonList, IonInput, IonItem, IonInputPasswordToggle, IonButtons, IonMenuButton, IonLabel, IonNote, IonButton, IonIcon, TranslatePipe]
})
export class RegistrationPage implements OnInit {
  public formEntrada: FormGroup;
  public formLogin: FormGroup;
  public isLoggedIn = false;
  public registerOption = true;
  constructor(private fb: FormBuilder, public authService: AuthService, private router: Router) {
    
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

    authService.user$.subscribe((user) => {
      if(user){
        this.isLoggedIn = true;
      }else{
        this.isLoggedIn = false;
      }
    })
   }

  ngOnInit() {
  }

  onClickRegister(){
    this.registerOption = true;
  }

  onClickLogin(){
    this.registerOption = false;
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

  onSubmitLogin(): void {
    const rawForm = this.formLogin.getRawValue();
    this.authService
      .login(rawForm.email, rawForm.password)
      .subscribe({
        next: () => {
        this.router.navigateByUrl('/');
      },
      error: (err) => {
        console.error(err.code);
      },
      });
  }
}
