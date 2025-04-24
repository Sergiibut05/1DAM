import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { IonButton, IonButtons, IonContent, IonHeader, IonIcon, IonInputPasswordToggle, IonItem, IonLabel, IonList, IonMenuButton, IonNote, IonSelectOption, IonTitle, IonToolbar } from '@ionic/angular/standalone';
import { BookCRUD } from 'src/app/services/books.service';
import { UserBookService } from 'src/app/services/userbook.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { Book } from 'src/app/interfaces/book.interface';

@Component({
  selector: 'app-publish-book',
  templateUrl: './publish-book.page.html',
  styleUrls: ['./publish-book.page.scss'],
  standalone: true,
  imports: [ReactiveFormsModule ,IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonList, IonLabel, IonNote, IonItem, IonButton, IonIcon, IonSelectOption, IonMenuButton, IonButtons]
})
export class PublishBookPage implements OnInit {
  public formEntrada: FormGroup;
  protected userData: any;
  constructor(private fb: FormBuilder, private bookService: BookCRUD, private userBookService: UserBookService, private router: Router, private auth: AuthService) {
    this.auth.user$.subscribe((user) => {
      if(user){
        this.userData = user;
      }
    })

    this.formEntrada = this.fb.group({
      name: ['', Validators.required],
      price: [0 , [Validators.required]],
      description: ['', [Validators.required]],
      releaseDate: ['', [Validators.required]],
      category: ['', [Validators.required]],
      photo: ['', [Validators.required]]
    });
   }

  ngOnInit() {
  }

  async onSubmitCreate(){
    const rawForm = this.formEntrada.getRawValue() as Book;
    const bookId = this.bookService.add(rawForm);
    this.userBookService.add(this.userData?.email!, await bookId);
    this.router.navigateByUrl('/your-products')
    this.formEntrada.reset();
  }
}
