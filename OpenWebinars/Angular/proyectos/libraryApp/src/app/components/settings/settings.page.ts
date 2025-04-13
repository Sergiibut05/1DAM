import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar, IonMenuButton, IonButtons, IonToggle} from '@ionic/angular/standalone';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.page.html',
  styleUrls: ['./settings.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonMenuButton, IonButtons, IonToggle]
})
export class SettingsPage implements OnInit {
  isDarkMode= false;

  constructor() {
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

}
