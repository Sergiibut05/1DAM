import { Component, OnInit } from '@angular/core';
import { ProveService } from '../../services/sesion.service';

@Component({
  selector: 'app-about-us',
  standalone: false,
  templateUrl: './about-us.component.html',
  styleUrl: './about-us.component.scss'
})
export class AboutUsComponent implements OnInit {
  public userData: any;
  
  constructor(private proveService: ProveService){}

  ngOnInit(): void {
    this.proveService.userData$.subscribe(
      (data: Object) => {
        this.userData = data;
      }
    );
    console.log(this.userData);
  }
}
