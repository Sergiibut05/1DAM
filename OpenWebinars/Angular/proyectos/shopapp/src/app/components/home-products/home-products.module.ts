import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ProductsComponent } from '../products/products.component';
import { CardsComponent } from '../cards/cards.component';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild([{path: '', component: ProductsComponent},
      {path: 'cards/:id', component: CardsComponent}
    ])
  ]
})
export class HomeProductsModule { }
