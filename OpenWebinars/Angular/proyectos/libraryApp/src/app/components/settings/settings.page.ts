import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar, IonMenuButton, IonButtons, IonToggle, IonSelect, IonSelectOption, IonLabel, IonButton} from '@ionic/angular/standalone';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.page.html',
  styleUrls: ['./settings.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonMenuButton, IonButtons, IonToggle, IonSelect, IonSelectOption, IonLabel, IonButton]
})
export class SettingsPage implements OnInit {
  isDarkMode= false;
  color = '';
  size = 'font-size-medium';
  userData: any;
  constructor(private auth: AuthService, private router: Router) {
    auth.user$.subscribe((user) => {
      if(user){
        this.userData = user;
      }
    })
    const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches
    this.isDarkMode = document.body.classList.contains('dark') || prefersDark;

    if(this.isDarkMode){
      document.body.classList.add('dark');
    }
   }

  toggleDarkMode() {
    this.isDarkMode
      ? document.body.classList.add('dark')
      : document.body.classList.remove('dark');
  }

  ngOnInit() {
  }

  onClickColor(event: Event){
    if(this.color){
      document.body.classList.remove(this.color);
    }
    this.color = (event.target as HTMLSelectElement).value
    document.body.classList.add(this.color);
  }
  onClickSize(event: Event){
    document.body.classList.remove(this.size);
    this.size = (event.target as HTMLSelectElement).value
    document.body.classList.add(this.size);
  }
  onClickLogOut(){
    this.auth.logout();
    this.router.navigate(['/registration'])
  }
}
