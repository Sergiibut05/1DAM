import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone: true,
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent {
  currentState = 'in'
  onClick(){
    this.currentState = this.currentState === 'in'? 'out':'in';
  }


  portraitimg = 'https://i.blogs.es/43c25c/rdr/840_560.jpeg'
  portrait = {
    border: '2px solid black',
    'border-radius': '8px',
    'box-shadow': '0pc 8px 9px rgba(0,0,0,0.4)',
    'max-width': '100%',
    'max-height': '80%',
    filter: 'grayscale(90%)',
    transition: 'transform 0.5s ease, filter 0.5s ease'
  }
  onMouseEnter(){
    console.log('entrando')
    this.portrait = {...this.portrait, filter: 'grayscale(0%)'}
  }
  onMouseLeave(){
    this.portrait = {...this.portrait, filter: 'grayscale(90%)'}
  }
  pages = {
    landingpage: '',
    main: '/main',
    missionsList: '/missions',
    form: '/form'
  }
}
