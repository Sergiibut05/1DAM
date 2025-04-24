import { Component, HostListener, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonButton, IonButtons, IonCard, IonContent, IonHeader, IonMenuButton, IonTitle, IonToolbar } from '@ionic/angular/standalone';
import { UserBookLikeService } from 'src/app/services/userBookLike.service';
import { Book } from 'src/app/interfaces/book.interface';
import { AuthService } from 'src/app/services/auth.service';
import { CartService } from 'src/app/services/cart.service';
import { Router } from '@angular/router';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-favourites',
  templateUrl: './favourites.page.html',
  styleUrls: ['./favourites.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonMenuButton, IonButtons, IonButton, IonCard, TranslatePipe]
})
export class FavouritesPage implements OnInit {
  public products: any[] = [];
  public isMobile: boolean = false;
  public isLoggedIn = false;

  constructor(private userBookLike: UserBookLikeService, private authService: AuthService, private cartService: CartService, private router: Router) { 
    this.checkViewport();
  }

  @HostListener('window:resize', [])
  onResize() {
    this.checkViewport();
  }

  private checkViewport() {
    this.isMobile = window.innerWidth < 768;
  }

  ngOnInit() {
    this.products = this.userBookLike.getAllBooksFromStorage();
  }

  onClickBuy(product: Book){
      this.authService.user$.subscribe((user) => {
        if(this.isLoggedIn) {
          this.cartService.addToCart(product);
        }else{
          this.router.navigateByUrl('/');
        }
      })
    }
  
    onClick(bookid: string){
      this.router.navigate([`/cards/${ bookid }`])
    }

}
