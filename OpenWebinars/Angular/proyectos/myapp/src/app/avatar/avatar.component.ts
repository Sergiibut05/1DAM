import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-avatar',
  standalone: true,  // Hacemos que este componente sea autónomo
  imports: [FormsModule],
  templateUrl: './avatar.component.html',
  styleUrl: './avatar.component.scss'
})
export class AvatarComponent {
  name = "Jhon"
  surname = "Marston"
  age = 33
  married = true
  adress = {
    country: 'EEUU',
    city: 'BlackWater',
    street: 'Liberty',
    img: "https://img.goodfon.com/original/1920x1080/e/4e/red-dead-redemption-geroy.jpg"
  }
  private tlf = '123456789'
  private nationality = 'American'
  gettlf(){
    return this.tlf;
  }
  imgborderstyle = '15px solid black'
  imgstyle = {
    filter: 'sepia(0)'
  }
  displayState = false
  buy(){
    console.log('El Producto va a ser comprado')
    this.displayState = true
  }
  adres = ''
  postaCode = 0
  onMouseClick(){
    console.log(`Address: ${this.adres}`)
    console.log('PostalCode: '+this.postaCode)
  }
  onMouseEnter(){
    console.log('Mouse Enter');
    this.imgstyle = {...this.imgstyle,  filter: 'sepia(0.9)'}
  }
  onMouseLeave(){
    console.log('Mouse Leave');
    this.imgstyle = {...this.imgstyle, filter: 'sepia(0)£'}
  }
}
