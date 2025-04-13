import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonButton, IonButtons, IonContent, IonHeader, IonMenuButton, IonTitle, IonToolbar } from '@ionic/angular/standalone';
import { AuthService } from 'src/app/services/auth.service';
import { PhotoService } from '../../services/photo.service';
import { FireStorageService } from 'src/app/services/fire-storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonButton, IonMenuButton, IonButtons]
})
export class ProfilePage implements OnInit {
  protected userData: any;
  protected isPhotoModalOpen = false;
  protected profilePhoto = '../assets/profile.png';
  public cropCoordinates: any = null;
  constructor(
    private authService: AuthService,
    public photoService: PhotoService
  ) {
    authService.user$.subscribe((user) => {
      if(user){
        this.userData = user;
        if(user.photoURL){
          this.profilePhoto = user.photoURL
        }
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
        this.profilePhoto = newPhotoUrl[0];
      }
    }catch (error) {
      console.error("Error al sacar la foto: ", error);
    }
    
  }
}
