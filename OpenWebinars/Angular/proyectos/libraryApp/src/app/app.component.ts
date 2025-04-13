
import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { IonApp, IonSplitPane, IonMenu, IonContent, IonList, IonListHeader, IonNote, IonMenuToggle, IonItem, IonIcon, IonLabel, IonRouterOutlet, IonRouterLink, IonImg } from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { mailOutline, mailSharp, paperPlaneSharp, heartOutline, heartSharp, archiveOutline, archiveSharp, trashOutline, trashSharp, bookmarkOutline, bookmarkSharp, star, bookSharp, bookOutline, settingsOutline, settingsSharp } from 'ionicons/icons';
import { AuthService } from './services/auth.service';
import { User } from 'firebase/auth';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
  imports: [RouterLink, RouterLinkActive, IonApp, IonSplitPane, IonMenu, IonContent, IonList, IonListHeader, IonNote, IonMenuToggle, IonItem, IonIcon, IonLabel, IonRouterLink, IonRouterOutlet, CommonModule],
})
export class AppComponent {
  public userData: User | null = null;
  public appPages = [
    { title: 'Login or Register', url: '/registration', icon: 'mail' },
    { title: 'Products', url: '/products', icon: 'book' },
    { title: 'Favorites', url: '/favourites', icon: 'heart' },
    { title: 'Your Books', url: '/your-products', icon: 'archive' },
    { title: 'Trash', url: '/folder/trash', icon: 'trash' },
    { title: 'Settings', url: '/settings', icon: 'settings' },
  ];
  constructor(private authService: AuthService, private router: Router) {
    
    authService.getCurrentFireStoreUser().then((user) => {
      this.userData = user as User;
    })
    addIcons({ mailOutline, mailSharp, paperPlaneSharp, heartOutline, heartSharp, archiveOutline, archiveSharp, trashOutline, trashSharp, bookmarkOutline, bookmarkSharp, star , bookOutline, bookSharp, settingsOutline, settingsSharp});
  }

  onClickProfile(){
    if(this.userData){
      this.router.navigate(['/profile'])
    }else{
      this.router.navigate(['/registration'])
    }
  }
}
