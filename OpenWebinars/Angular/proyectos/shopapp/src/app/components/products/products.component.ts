import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { AuthService } from '../../services/auth.service';
import { BookCRUD } from '../../services/books.service';
import { Book } from '../Book.interface';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserBookService } from '../../services/userbook.service';
import { User } from '@angular/fire/auth';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  providers: [],
  templateUrl: './products.component.html',
  styleUrl: './products.component.scss'
})
export class ProductsComponent implements OnInit{
  public seeYourBooks = false;
  public yourProducts: any[] = [];
  public products: any[] = [];
  public userData: User | null = null;
  public productsClicked= new Map<Object, number>();
  public sellOption = false;
  public isLoggedIn = false;
  public formBook: FormGroup;
  public username: any;
  public category = '';

  constructor( 
    private userbookservice: UserBookService,
    private fb: FormBuilder,
    private cd: ChangeDetectorRef,
    private authService: AuthService,
    private router: Router,
    private bookService: BookCRUD,
    private cartService: CartService) {
      this.authService.user$.subscribe((user) => {
        if(user) {
          this.isLoggedIn = true;
        }else{
          this.isLoggedIn = false;
        }
      })

      this.formBook = this.fb.group({
        name: ['', Validators.required],
        price: [0 , [Validators.required]],
        description: ['', [Validators.required]],
        releaseDate: ['', [Validators.required]],
        category: ['', [Validators.required]],
        photo: ['', [Validators.required]]
      });
    }

  ngOnInit(): void {
    this.loadBooks();
    this.authService.user$.subscribe(
      (user) => {
        if(user){
          this.userData = user as User;
          this.username = this.userData.displayName;
        }
        
      }
    );

  }
  onClick(bookid: string){
    this.router.navigate([`/home/products/cards/${ bookid }`])
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
  onClickBtnBuy(){
    this.sellOption = false;
  }
  onClickSell(){
    this.sellOption = true;
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

  async onSubmitBook(){
    const rawForm = this.formBook.getRawValue() as Book;
    const bookId = this.bookService.add(rawForm);
    this.userbookservice.add(this.userData?.email!, await bookId);
    this.router.navigateByUrl('/home/products')
    this.formBook.reset();
  }

  onClickSeeYourBooks() {
    this.userbookservice.getAllBooksReferencesByUser(this.userData?.email!).subscribe((uidBooks) => {
      const bookCollection: Book[] = []
      uidBooks.forEach((uidbook) => {
        this.bookService.getBookById(uidbook).subscribe((book) => {
          if(bookCollection.includes(book)){
            
          }else{
            bookCollection.push(book);
          }
        })
      })
      this.yourProducts = bookCollection;
      this.seeYourBooks = !this.seeYourBooks;
    })
  }
  onClickDelete(bookid: string){
    this.bookService.delete(bookid);
    this.userbookservice.deleteByBookId(bookid);
  }
  onClickCategory(event: Event){
    this.category = (event.target as HTMLSelectElement).value
    this.loadBooks();
  }
  
}
