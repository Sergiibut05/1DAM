import { Component, OnInit } from '@angular/core';
import { EntriesService } from '../../services/entries.service';
import { CommonModule } from '@angular/common';
import { ProveService } from '../../services/sesion.service';
import { Router } from '@angular/router';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule],
  providers: [],
  templateUrl: './products.component.html',
  styleUrl: './products.component.scss'
})
export class ProductsComponent implements OnInit{
  public products: any[] = [];
  public userData: any;
  public productsClicked= new Map<Object, number>();

  constructor(private entriesService: EntriesService, 
    private proveService: ProveService, 
    private router: Router,
    private cartService: CartService) {}

  ngOnInit(): void {
    this.entriesService.getData().subscribe((data) => {
      this.products = data;
    })

    this.proveService.userData$.subscribe(
      (data: Object) => {
        this.userData = data;
      }
    );

  }
  onClick(id:number){
    this.router.navigate([`/home/products/cards/${ id }`])
  }
  onClickBuy(product: Object){
    this.cartService.addToCart(product);
  }
}
