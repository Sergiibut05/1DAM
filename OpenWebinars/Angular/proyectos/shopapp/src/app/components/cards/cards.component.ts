import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EntriesService } from '../../services/entries.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cards',
  imports: [CommonModule],
  templateUrl: './cards.component.html',
  styleUrl: './cards.component.scss'
})
export class CardsComponent implements OnInit{
  public products: any[] = [];
  public id: number = 0;
  public selectProduct: any;

  constructor(private activatedRoute: ActivatedRoute, private entriesService: EntriesService) {
    this.activatedRoute.params.subscribe( paramsUrl => {
      this.id = +paramsUrl['id'];
    });
  }

  ngOnInit(): void {
    this.entriesService.getData().subscribe((data) => {
      this.products = data;
      this.products.forEach(product => {
        if(product.id === this.id){
          this.selectProduct = product;
        }
      });
    });
  }
}
