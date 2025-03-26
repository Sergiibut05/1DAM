import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ComponentsModule } from './components/components.module';
import { CommonModule } from '@angular/common'; 
import { CharacterInfoComponent } from './components/character-info/character-info.component';


export const habilities = [
  { name: 'Tiroteos', 
    description: 'Marston es un tirador excepcionalmente hábil, capaz de usar una variedad de armas, incluyendo pistolas, rifles y arcos. Su precisión y destreza en combate son características definitorias de su personaje.' },
  { name: 'Combate cuerpo a cuerpo',
    description: 'Marston es un luchador formidable, capaz de derrotar a varios oponentes simultáneamente. Su habilidad para esquivar y contraatacar es una de sus mayores fortalezas.' },
  {name: 'Cazar y pescar',
    description: 'Marston es un cazador y pescador experto, capaz de rastrear y matar animales salvajes para obtener recursos. Su habilidad para rastrear y cazar presas es una de sus habilidades más valiosas.'},
  {name: 'Montar a caballo',
    description: 'Marston es un jinete experto, capaz de domar y montar caballos salvajes. Su habilidad para controlar y dirigir a los caballos es una de sus habilidades más valiosas.'},
  {name: 'Redención y toma de decisiones morales',
    description: 'Marston es un personaje complejo, con una historia de redención y toma de decisiones morales. Su capacidad para elegir entre el bien y el mal, y su lucha interna por redimirse de su pasado, son aspectos clave de su personaje.'}
];

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ComponentsModule, CommonModule, CharacterInfoComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'readDeadapp';
  habilities: any;
  ngOnInit(): void {
    this.habilities = habilities;
  }
}
