import { Component, NgModule } from '@angular/core';
@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  navbar = {
    img: "https://cdn.cookielaw.org/logos/3efb95b4-aed7-4aa8-85d8-488eb074fa8c/8d9316c1-2ab6-4a2f-9582-ad18d8bfede6/4439df62-7489-4d15-b892-f1b5b6dd029d/Rockstar_Games_Logo.png",
    name: "Rockstar Games"
  }

  pages = {
    landingpage: '',
    main: '/main',
    missionsList: '/missions',
    form: '/form'
  }
}
