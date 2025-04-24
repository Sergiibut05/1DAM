import { Component, HostListener, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar, IonMenuButton, IonButtons, IonButton, IonCard, Platform, IonNote, IonSelect, IonSelectOption, IonLabel, IonIcon, IonSpinner } from '@ionic/angular/standalone';
import { BookCRUD } from 'src/app/services/books.service';
import { Book } from 'src/app/interfaces/book.interface';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { CartService } from 'src/app/services/cart.service';
import { addIcons } from 'ionicons';
import { heart } from 'ionicons/icons';
import { UserBookLikeService } from 'src/app/services/userBookLike.service';
import { TranslatePipe } from '@ngx-translate/core';


@Component({
  selector: 'app-products',
  templateUrl: './products.page.html',
  styleUrls: ['./products.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonButtons, IonMenuButton, IonButton, IonCard, IonNote, IonSelect, IonSelectOption, IonLabel, IonIcon, IonSpinner, TranslatePipe]
})
export class ProductsPage implements OnInit {
  
  public category = '';
  public isLoggedIn = false;
  protected userData: any;
  public isMobile = false;
  public likedProducts: any;

  public products: any[] = [];
  private lastDoc: any = null;
  private isLoading = false;
  protected isEnd = false;
  private limit = 10;
  isLoadingMore = false;

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
    this.loadMoreBooks();
    this.likedProducts = this.userBookLike.getAllBooksFromStorage();

  }

  @HostListener('window:resize', [])
  onResize() {
    this.checkViewport();
  }

  @ViewChild('contentRef', { static: true }) contentRef!: IonContent;

  onIonScroll(event: any): void {
    this.contentRef.getScrollElement().then(scrollEl => {
      const threshold = 100;
      const scrollPosition = scrollEl.scrollTop + scrollEl.clientHeight;
      const maxScroll = scrollEl.scrollHeight;

      if (scrollPosition >= maxScroll - threshold) {
        this.loadMoreBooks();
      }
    });
  }

  private checkViewport() {
    this.isMobile = window.innerWidth < 768;
  }

  loadMoreBooks() {
    if (this.isLoadingMore || this.isEnd) return; // Evita cargar si ya está cargando o si ya no hay más productos.
  
    this.isLoadingMore = true;
  
    this.bookService.getPaginated(this.limit, this.lastDoc, this.category).then(({ books, lastDoc }) => {
      // Filtrar productos duplicados por ID antes de añadirlos
      const newBooks = books.filter((book: Book) => 
        !this.products.some((p: Book) => p.id === book.id)
      );
  
      // Agregar solo los productos nuevos (no duplicados)
      this.products = [...this.products, ...newBooks];
      this.lastDoc = lastDoc;
  
      // Si no hay más productos, establece isEnd a true
      if (!this.lastDoc) {
        this.isEnd = true;  // Indicar que ya no hay más productos que cargar
      }
  
      this.isLoadingMore = false;
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
    this.router.navigate([`/cards/${ bookid }`])
  }

  onClickCategory(event: Event) {
    this.category = (event.target as HTMLSelectElement).value;
    this.products = []; 
    this.lastDoc = null;  
    this.isEnd = false;   
    this.loadMoreBooks(); 
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
