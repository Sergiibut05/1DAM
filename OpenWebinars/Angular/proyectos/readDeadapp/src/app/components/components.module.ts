import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common'; // Módulo común para usar ngIf, ngFor, etc.
import { HeaderComponent } from './header/header.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { HabilitiesArrayComponent } from './habilities-array/habilities-array.component';



@NgModule({
  declarations: [
    HeaderComponent,
    NavbarComponent,
    FooterComponent,
    HabilitiesArrayComponent,
    
  ],
  imports: [
    CommonModule
  ],
  exports: [
    HeaderComponent,
    NavbarComponent,
    FooterComponent,
    HabilitiesArrayComponent,
    
  ]
})
export class ComponentsModule { }
