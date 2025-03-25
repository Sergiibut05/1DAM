import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common'; // Módulo común para usar ngIf, ngFor, etc.
import { HeaderComponent } from './header/header.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { CharacterInfoComponent } from './character-info/character-info.component';


@NgModule({
  declarations: [
    HeaderComponent,
    NavbarComponent,
    FooterComponent,
    CharacterInfoComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    HeaderComponent,
    NavbarComponent,
    FooterComponent,
    CharacterInfoComponent
  ]
})
export class ComponentsModule { }
