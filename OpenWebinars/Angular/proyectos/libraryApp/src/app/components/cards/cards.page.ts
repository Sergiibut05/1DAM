import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonContent, IonItem, IonLabel, IonImg, IonSelectOption, IonButton, IonCard, IonInput, IonSelect, IonDatetime } from '@ionic/angular/standalone';
import { Book } from 'src/app/interfaces/book.interface';
import { User } from 'firebase/auth';
import { UserBookService } from 'src/app/services/userbook.service';
import { AuthService } from 'src/app/services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';
import { BookCRUD } from 'src/app/services/books.service';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-cards',
  templateUrl: './cards.page.html',
  styleUrls: ['./cards.page.scss'],
  standalone: true,
  imports: [ReactiveFormsModule ,IonContent, CommonModule, FormsModule, IonButton, IonImg, IonCard, IonItem, IonLabel, IonSelectOption, IonInput, IonSelect, IonDatetime, TranslatePipe]
})
export class CardsPage implements OnInit{
  public product!: Book;
  public id: string = '';
  public editMode = false;
  public formBook: FormGroup;
  public userData: User | null = null;
  public previousUrl: string | undefined = undefined;

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
    this.previousUrl = this.router.getCurrentNavigation()?.previousNavigation?.finalUrl?.toString();
    

  }
  onClickBuy(product: Book){
    this.cartService.addToCart(product);
  }
  onClick(){
    if(this.previousUrl){
      this.router.navigate([this.previousUrl]);
    }else{
      this.router.navigate([`/products`]);
    }
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


