import { Component, OnInit } from '@angular/core';
import { TranslatePipe, TranslateService, TranslateModule } from '@ngx-translate/core';
import { CommonModule } from '@angular/common';
import translationsEN from '../../../../public/i18n/en.json';
import translationsES from '../../../../public/i18n/es.json';

@Component({
  selector: 'app-character-info',
  standalone: true,
  imports: [
    CommonModule,
    TranslatePipe,
    TranslateModule
  ],
  templateUrl: './character-info.component.html',
  styleUrls: ['./character-info.component.scss']
})
export class CharacterInfoComponent implements OnInit {
  history: any;

  public currentLang = 'ES';
  constructor(private translate: TranslateService) {
    this.translate.setTranslation('es', translationsES);
    this.translate.setTranslation('en', translationsEN);
    this.translate.setDefaultLang('es'); 

    this.history = {
      name: '',
      description: '',
      habilidades: []
    };
  }

  ngOnInit(): void {
    this.history = this.translate.instant('history');
  }

  changeLang(lang: string) {
    this.translate.use(lang);
    this.currentLang = lang === 'es' ? 'ES' : 'EN';
    this.history = this.translate.instant('history');
  }
}