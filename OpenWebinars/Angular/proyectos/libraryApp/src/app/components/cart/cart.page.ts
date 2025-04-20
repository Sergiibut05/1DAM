import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonButton, IonButtons, IonContent, IonFooter, IonHeader, IonImg, IonItem, IonLabel, IonText, IonTitle, IonToolbar } from '@ionic/angular/standalone';
import { AuthService } from 'src/app/services/auth.service';
import { CartService } from 'src/app/services/cart.service';
import { Router } from '@angular/router';
import { Book } from 'src/app/interfaces/book.interface';
import { User } from 'firebase/auth';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.page.html',
  styleUrls: ['./cart.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonButton, IonText, IonItem, IonImg, IonLabel, IonButtons, IonFooter]
})
export class CartPage implements OnInit {
  public productsCart: [Book, number][] = [];
  public totalPrice: number = 0;
  public userData: User | null = null;
  public username: string | null = null; 

  constructor(private authService: AuthService,private cartService: CartService, private cdRef: ChangeDetectorRef, private router: Router) { }

  ngOnInit(){
    this.authService.user$.subscribe(
          (user) => {
            if(user){
              this.userData = user as User;
              this.username = this.userData.displayName;
            }
            
          }
        );
        this.refreshCart();
    
    
  }
  refreshCart(){
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
    this.router.navigateByUrl('/products');
  }
  onClickDeleteOne(product: Book){
    try{
      this.cartService.deleteOneFromCart(product)
      if(this.productsCart[0][1]===1 && this.productsCart.length === 1){
        this.redirectToProduct();
      }
    }catch (error) {
      console.error("Ocurrio un error",error);
    }finally {
      this.refreshCart();
    }
  }
  onClickDelete(product: Book){
    try{
      this.cartService.deleteFromCart(product)
      console.log("products: ",this.productsCart)
      if(this.productsCart[0][1]===1 && this.productsCart.length === 1){
        this.redirectToProduct();
      }
    }catch (error) {
      console.error("Ocurrio un error",error);
    }finally{
      this.refreshCart();
    }
  }
}
