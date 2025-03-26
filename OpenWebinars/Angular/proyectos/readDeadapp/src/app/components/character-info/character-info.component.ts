import { Component, Input} from '@angular/core';
import { InfoService} from '../../services/information/info.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-character-info',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './character-info.component.html',
  styleUrl: './character-info.component.scss'
})
export class CharacterInfoComponent {
  @Input() habilitie: any;

  /*constructor(private infoService: InfoService) {}*/
  history: any;
  /*ngOnInit(): void {
    this.infoService.getInfo().subscribe((response: any) => {
    this.history = response.history;
    });
  }*/
}
