import { Component, HostListener, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar, IonCard, IonMenuButton, IonButtons, IonButton, IonText } from '@ionic/angular/standalone';
import { BookCRUD } from 'src/app/services/books.service';
import { Router } from '@angular/router';
import { UserBookService } from 'src/app/services/userbook.service';
import { AuthService } from 'src/app/services/auth.service';
import { Book } from 'src/app/interfaces/book.interface';
import { forkJoin } from 'rxjs';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-your-products',
  templateUrl: './your-products.page.html',
  styleUrls: ['./your-products.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonCard, IonButtons, IonMenuButton, IonButton, IonText, TranslatePipe]
})
export class YourProductsPage{
  public isMobile = false;
  protected products: any[] = [];
  protected userData: any;
  constructor(private bookService: BookCRUD, private router: Router, private userBookService: UserBookService, private auth: AuthService) {
    this.checkViewport();
    this.auth.user$.subscribe((user) => {
      this.userData = user
      if(this.userData){
        this.loadBooks();
      }
    })
   }

  loadBooks(){
    this.userBookService.getAllBooksReferencesByUser(this.userData?.email!).subscribe((uidBooks) => {
      console.log(uidBooks)
      const bookRequests = uidBooks.map((uid) => this.bookService.getBookById(uid));
    
      forkJoin(bookRequests).subscribe((books: Book[]) => {
        this.products = books;
        console.log(this.products);
      });
    });
    }

  @HostListener('window:resize', [])
  onResize() {
    this.checkViewport();
  }

  private checkViewport() {
    this.isMobile = window.innerWidth < 768;
  }

  onClickDelete(bookid: string){
    this.bookService.delete(bookid);
    this.userBookService.deleteByBookId(bookid);
  }

  onClick(bookid: string){
    this.router.navigate([`/cards/${ bookid }`])
  }
  onClickPublish(){
    this.router.navigate(['/publish-book'])
  }
}
