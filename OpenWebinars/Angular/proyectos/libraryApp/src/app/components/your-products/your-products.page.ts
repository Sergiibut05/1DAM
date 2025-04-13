import { Component, HostListener, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar, IonCard, IonMenuButton, IonButtons } from '@ionic/angular/standalone';
import { BookCRUD } from 'src/app/services/books.service';
import { Router } from '@angular/router';
import { UserBookService } from 'src/app/services/userbook.service';

@Component({
  selector: 'app-your-products',
  templateUrl: './your-products.page.html',
  styleUrls: ['./your-products.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonCard, IonButtons, IonMenuButton]
})
export class YourProductsPage implements OnInit {
  public isMobile = false;
  protected products: any[] = [];

  constructor(private bookService: BookCRUD, private router: Router, private userBookService: UserBookService) {
    this.checkViewport();
   }

  ngOnInit() {
    this.loadBooks();
  }

  loadBooks(){
    this.bookService.getAll().subscribe((data) => {
        this.products = data;
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
}
