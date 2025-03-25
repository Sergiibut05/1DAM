import { Component } from '@angular/core';
import { trigger, state, style, animate, transition } from '@angular/animations';

@Component({
  selector: 'app-header',
  standalone: false,
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
  animations: [
    trigger('FadeInOut', [
      state('in', style({
        opacity: 1,
      })),
      state('out', style({
        opacity: 0
      })),
      transition('in => out', [
        animate('500ms ease-out')
      ]),
      transition('out => in', [
        animate('500ms ease-out')
      ])
    ])
  ]
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
    'max-width': '80%',
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
}
