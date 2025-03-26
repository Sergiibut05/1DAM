import { Component, Input } from '@angular/core';


@Component({
  selector: 'app-habilities-array',
  standalone: false,
  templateUrl: './habilities-array.component.html',
  styleUrl: './habilities-array.component.scss'
})
export class HabilitiesArrayComponent {
  @Input() habilitie: any;
}
