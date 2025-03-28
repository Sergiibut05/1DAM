import { Injectable } from '@angular/core';
import { EntriesService } from './entries.service';
import { Observable, of } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CartService {
  private products = new Map<Object, number>();
  constructor(private entriesService: EntriesService) { 
    this.entriesService.getData().subscribe((data) => {
      data.forEach((element: Object) => {
        this.products.set(element, 0);
      });
    });
  }

  addToCart(product: Object){
    const productData = this.products.get(product);
    this.products.set(product, !productData?1:productData+1)
  }

  getCart(): Observable<[Object, number][]>{
    const filteredProducts = Array.from(this.products.entries())
    .filter(([product, quantity]) => quantity > 0);
    return of(filteredProducts);
  }
}
