
import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { IonApp, IonSplitPane, IonMenu, IonContent, IonList, IonListHeader, IonNote, IonMenuToggle, IonItem, IonIcon, IonLabel, IonRouterOutlet, IonRouterLink, IonImg, IonFabButton, IonFab, IonFabList } from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { mailOutline, mailSharp, paperPlaneSharp, heartOutline, heartSharp, archiveOutline, archiveSharp, trashOutline, trashSharp, bookmarkOutline, bookmarkSharp, star, bookSharp, bookOutline, settingsOutline, settingsSharp, planetOutline, planetSharp, cartOutline, cartSharp, chevronDownCircle, language } from 'ionicons/icons';
import { AuthService } from './services/auth.service';
import { User } from 'firebase/auth';
import { CommonModule } from '@angular/common';
import { TranslatePipe, TranslateService } from '@ngx-translate/core';
import { LanguageService } from './services/language.service';


@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
  imports: [RouterLink, RouterLinkActive, IonApp, IonSplitPane, IonMenu, IonContent, IonList, IonListHeader, IonNote, IonMenuToggle, IonItem, IonIcon, IonLabel, IonRouterLink, IonRouterOutlet, CommonModule, IonFabButton, IonFab, IonFabList,],
})
export class AppComponent {
  public userData: User | null = null;
  public userPhoto = '';
  public appPages: any[] = [];
  public currentLang: string;
  constructor(private authService: AuthService, private router: Router, private languageService: LanguageService) {
    this.currentLang = this.languageService.getCurrentLang();

    this.loadAppPages();

    authService.user$.subscribe((user) => {
      this.userData = user as User;
      if(user){
        authService.getCurrentFireStoreUser().then((userf) => {
          if(userf?.photoURL){
            this.userPhoto = userf?.photoURL;
          }else{
            this.userPhoto = '.assets/profile.png'
          }
        })
      }
    })
    addIcons({ mailOutline, mailSharp, paperPlaneSharp, heartOutline, heartSharp, archiveOutline, archiveSharp, bookmarkOutline, bookmarkSharp, star , bookOutline, bookSharp, settingsOutline, settingsSharp, planetOutline, planetSharp, cartOutline, cartSharp, language});
  }

  async loadAppPages() {
    this.appPages = [
      { title: await this.languageService.getTranslationAsync('MENU.INIT'), url: '/book-animation', icon: 'planet' },
      { title: await this.languageService.getTranslationAsync('MENU.LOGIN'), url: '/registration', icon: 'mail' },
      { title: await this.languageService.getTranslationAsync('MENU.PRODUCTS'), url: '/products', icon: 'book' },
      { title: await this.languageService.getTranslationAsync('MENU.CART'), url: '/cart', icon: 'cart' },
      { title: await this.languageService.getTranslationAsync('MENU.FAVORITES'), url: '/favourites', icon: 'heart' },
      { title: await this.languageService.getTranslationAsync('MENU.YOURBOOKS'), url: '/your-products', icon: 'archive' },
      { title: await this.languageService.getTranslationAsync('MENU.SETTINGS'), url: '/settings', icon: 'settings' },
    ];
  }

  onClickProfile(){
    if(this.userData){
      this.router.navigate(['/profile'])
    }else{
      this.router.navigate(['/registration'])
    }
  }

  changeLanguage(lang: string) {
    this.languageService.changeLanguage(lang);
    this.languageService.storeLanguage(lang);
    this.currentLang = lang;
    this.loadAppPages();
  }
  
}
