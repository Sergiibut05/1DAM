import { Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { CommonModule } from '@angular/common';
import { Product } from './Product.interface';

@Component({
  selector: 'app-cart',
  imports: [CommonModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})
export class CartComponent implements OnInit{
  public productsCart: [Product, number][] = [];
  public totalPrice: number = 0;

  constructor(private cartService: CartService) {}
  ngOnInit(){
    this.cartService.getCart().subscribe((data) => {
      this.productsCart = data as [Product, number][]
    })
    this.getTotalPrice();
  }
  getTotalPrice(){
    this.totalPrice = 0;
    this.productsCart.forEach(element => {
      this.totalPrice += element[0].precio*element[1];
    });
  }
}
