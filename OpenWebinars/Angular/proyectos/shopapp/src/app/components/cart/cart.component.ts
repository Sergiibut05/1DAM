import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { Book } from '../Book.interface';
import { Router } from '@angular/router';
import { EmailService } from '../../services/email.service';
import { AuthService } from '../../services/auth.service';
import { user, User } from '@angular/fire/auth';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-cart',
  imports: [CommonModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})
export class CartComponent implements OnInit{
  public productsCart: [Book, number][] = [];
  public totalPrice: number = 0;
  public userData: User | null = null;
  public username: string | null = null; 

  constructor(private http: HttpClient,private authService: AuthService,private cartService: CartService, private cdRef: ChangeDetectorRef, private router: Router, private emailservice: EmailService) { }
  ngOnInit(){
    this.authService.user$.subscribe(
          (user) => {
            if(user){
              this.userData = user as User;
              this.username = this.userData.displayName;
            }
            
          }
        );

    this.cartService.getCart().subscribe((data) => {
      if (data && data.length > 0) {
        this.productsCart = data
        this.getTotalPrice();
        this.cdRef.detectChanges();
        console.log('productsCart:', this.productsCart);
      }
      
    })
    
  }
  getTotalPrice(){
    this.totalPrice = 0;
    this.productsCart.forEach(element => {
      this.totalPrice += element[0].price*element[1];
    });
  }
  redirectToProduct(){
    this.router.navigateByUrl('/home/products');
  }

  onClickSendEmail(){
    let htmlContent = `
  <div style="text-align: center; padding: 20px;">
    <h1 style="font-size: 2rem; color: #333; margin-bottom: 20px;">Your Cart</h1>
`;

this.productsCart.forEach((product) => {
  htmlContent += `
    <div style="display: flex; display: flex; padding-left: 25%; margin-bottom: 20px; border-bottom: 1px solid #ddd; padding-bottom: 10px;">
      <img style="padding-left: 4px; height: 100px; width: 100px; object-fit: cover; margin-bottom: 10px;" src="${product[0].photo}" alt="${product[0].name}">
      <p style="padding-left: 4px; font-size: 1.2rem; color: #333; margin: 5px 0;">${product[0].name}</p>
      <p style="padding-left: 10px; font-size: 1rem; color: #555; margin: 5px 0;">[Price per Product: ${product[0].price}]</p>
      <p style="padding-left: 10px; font-size: 1rem; color: #555; margin: 5px 0; text-align: center;">X</p>
      <p style="padding-left: 10px; font-size: 1rem; color: #555; margin: 5px 0;">[Quantity: ${product[1]}]</p>
      <p style="padding-left: 10px; font-size: 1.2rem; color: #333; margin: 5px 0;">Total Product Price: ${product[0].price * product[1]}</p>
    </div>
  `;
  });
  const formattedTotalPrice = new Intl.NumberFormat('es-ES', {
    style: 'currency',
    currency: 'EUR'
  }).format(this.totalPrice);

  htmlContent += `
      <h4 style="font-size: 1.5rem; color: #333; margin-top: 20px;">Total: ${formattedTotalPrice}</h4>
    </div>
  `;


    this.http.post('http://localhost:3000/send-email', {to: this.userData?.email, subject: '¡Your Library Shop Cart!', htmlContent: htmlContent
    }).subscribe({
      next: (response) => console.log('Correo enviado: ', response),
      error: (error) => console.error('Error enviado correo:', error),
    });
}
}
