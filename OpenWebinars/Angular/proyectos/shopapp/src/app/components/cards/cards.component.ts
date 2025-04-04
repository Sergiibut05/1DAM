import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CartService } from '../../services/cart.service';
import { Book } from '../Book.interface';
import { BookCRUD } from '../../services/books.service';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { error } from 'console';
import { response } from 'express';
import { AuthService } from '../../services/auth.service';
import { User } from '@angular/fire/auth';
import { UserBookService } from '../../services/userbook.service';

@Component({
  selector: 'app-cards',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './cards.component.html',
  styleUrl: './cards.component.scss'
})
export class CardsComponent implements OnInit{
  public product!: Book;
  public id: string = '';
  public editMode = false;
  public formBook: FormGroup;
  public userData: User | null = null;

  constructor(private userBookService: UserBookService,private authService: AuthService,private fb: FormBuilder,private activatedRoute: ActivatedRoute, private bookService: BookCRUD, private cartService: CartService, private router: Router) {
    
    this.activatedRoute.paramMap.subscribe( paramsUrl => {
      this.id = paramsUrl.get('id') || '';
    });

    this.formBook = this.fb.group({
      name: ['',],
      price: [0 ,],
      description: ['',],
      releaseDate: ['',],
      category: ['',],
      photo: ['',]
    });
  }

  ngOnInit(): void {
    this.bookService.getBookById(this.id).subscribe((data) => {
      this.product = data;
    })
    this.authService.user$.subscribe(
      async (user) => {
        if(user){
          this.userData = user as User;
          try{
            const ownerEmail = await this.userBookService.getUserUidByBookId(this.id);

            if(ownerEmail && this.userData.email===ownerEmail){
              this.editMode = true;
            }
          }catch (error) {
            console.error("Error al obtener el dueño del libro:", error);
          }
        }
      }
    );
    

  }
  onClickBuy(product: Book){
    this.cartService.addToCart(product);
  }
  onClick(){
    this.router.navigate([`/home/products/`])
  }
  onClickUpdateBook(){
    const rawForm = this.formBook.getRawValue() as Book;
    if(!rawForm.category){
      rawForm.category=this.product.category
    }
    if(!rawForm.description){
      rawForm.description = this.product.description
    }
    if(!rawForm.name){
      rawForm.name = this.product.name
    }
    if(!rawForm.photo){
      rawForm.photo = this.product.photo
    }
    if(!rawForm.price){
      rawForm.price = this.product.price
    }
    if(!rawForm.releaseDate){
      rawForm.releaseDate = this.product.releaseDate
    }
    this.bookService.updateBook(rawForm, this.id).then(
      (response: any) => {console.log('Se ha Editado Correctamente:',response)}).catch(
      (error) => {console.error('Error al Editar el Libro:',error)})
      this.onClick();
    }
    
}

