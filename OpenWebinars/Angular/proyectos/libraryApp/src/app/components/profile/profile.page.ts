import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonButton, IonButtons, IonContent, IonHeader, IonMenuButton, IonTitle, IonToolbar } from '@ionic/angular/standalone';
import { AuthService } from 'src/app/services/auth.service';
import { PhotoService } from '../../services/photo.service';
import { FireStorageService } from 'src/app/services/fire-storage.service';
import { LanguageService } from 'src/app/services/language.service';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonButton, IonMenuButton, IonButtons, TranslatePipe]
})
export class ProfilePage implements OnInit {
  protected userData: any;
  protected isPhotoModalOpen = false;
  protected profilePhoto = '../assets/profile.png';
  public currentLang: string;
  constructor(
    private authService: AuthService,
    public photoService: PhotoService,
    public fireStorage: FireStorageService,
    private languageService: LanguageService
  ) {
    this.currentLang = this.languageService.getCurrentLang();

    authService.user$.subscribe((user) => {
      if(user){
        this.userData = user;
        authService.getCurrentFireStoreUser().then((userf) => {
          if(userf?.photoURL){
            this.profilePhoto = userf.photoURL
          }
        })
      }
    })
   }

  ngOnInit() {
  }

  onClickSeePhoto() {
    this.isPhotoModalOpen = true;
  }
  
  closePhotoModal() {
    this.isPhotoModalOpen = false;
  }

  async createNewProfilePhoto() {
    try{
      const newPhotoUrl = await this.photoService.makeNewProfilePhoto();
      if(newPhotoUrl){
        if(this.profilePhoto.indexOf('firebase')){
          const oldPhotoUrl = this.profilePhoto;
          this.profilePhoto = newPhotoUrl[0];
          this.fireStorage.deleteFileFromUrl(oldPhotoUrl);
        }else{
          this.profilePhoto = newPhotoUrl[0];
        }
      }
    }catch (error) {
      console.error("Error al sacar la foto: ", error);
    }
    
  }
}
