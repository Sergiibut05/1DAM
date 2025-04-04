import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Book } from '../components/Book.interface';
import { BookCRUD } from './books.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private products = new Map<Book, number>();
  constructor(private bookservice: BookCRUD) { 
    this.bookservice.getAll().subscribe((elements) => {
      elements.forEach((element: Book) => {
        this.products.set(element, 0);
      });
    });
  }
  
  addToCart(product: Book){
    const productData = this.products.get(product);
    this.products.set(product, !productData?1:productData+1)
  }

  deleteOneFromCart(product: Book){
    const productData = this.products.get(product);
    try{
      this.products.set(product, !productData?0:productData-1)
      
    }catch(error) {
      console.error("Ocurrió un error:", error);
    } 
    
  }

  deleteFromCart(product: Book){
    const productData = this.products.get(product);
    this.products.set(product, 0);
  }

  getCart(): Observable<[Book, number][]>{
    const filteredProducts = Array.from(this.products.entries())
    .filter(([product, quantity]) => quantity > 0);
    return of(filteredProducts);
  }
}
