import { Component, HostListener, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar, IonMenuButton, IonButtons, IonButton, IonCard, Platform, IonNote, IonSelect, IonSelectOption, IonLabel, IonIcon } from '@ionic/angular/standalone';
import { BookCRUD } from 'src/app/services/books.service';
import { Book } from 'src/app/interfaces/book.interface';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { CartService } from 'src/app/services/cart.service';
import { addIcons } from 'ionicons';
import { heart } from 'ionicons/icons';
import { UserBookLikeService } from 'src/app/services/userBookLike.service';


@Component({
  selector: 'app-products',
  templateUrl: './products.page.html',
  styleUrls: ['./products.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonButtons, IonMenuButton, IonButton, IonCard, IonNote, IonSelect, IonSelectOption, IonLabel, IonIcon]
})
export class ProductsPage implements OnInit {
  
  public category = '';
  protected products: any[] = [];
  public isLoggedIn = false;
  protected userData: any;
  public isMobile = false;
  public likedProducts: any;

  constructor(
    private bookService: BookCRUD,
    private router: Router,
    private authService: AuthService,
    private cartService: CartService,
    private userBookLike: UserBookLikeService
  ) { 
    this.checkViewport();
    addIcons({heart})

    authService.user$.subscribe((user) => {
      if(user){
        this.isLoggedIn = true;
        this.userData = user;
      }else{
        this.isLoggedIn = false;
        this.userData = {};
      }
    })
  }

  ngOnInit() {
    this.loadBooks();
    this.likedProducts = this.userBookLike.getAllBooksFromStorage();
  }

  @HostListener('window:resize', [])
  onResize() {
    this.checkViewport();
  }

  private checkViewport() {
    this.isMobile = window.innerWidth < 768;
  }

  loadBooks(){
    this.bookService.getAll().subscribe((data) => {
      if (this.category) {
        this.products = data.filter((book) => book.category === this.category);
      } else {
        this.products = data;
      }
    });
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
    this.router.navigate([`/home/products/cards/${ bookid }`])
  }

  onClickCategory(event: Event){
    this.category = (event.target as HTMLSelectElement).value
    this.loadBooks();
  }

  async onClickLike(book: Book) {
    if (this.isBookLiked(book)) {
      await this.userBookLike.deleteByBookId(book.id!);
    } else {
      await this.userBookLike.add(book);
    }
    this.likedProducts = this.userBookLike.getAllBooksFromStorage(); // ¡Actualizar!
  }

  isBookLiked(book: Book): boolean {
    return this.likedProducts?.some((b: Book) => b.id === book.id);
  }

}
