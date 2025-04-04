import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-about-us',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './about-us.component.html',
  styleUrl: './about-us.component.scss'
})
export class AboutUsComponent implements OnInit {
  public userData: any;
  public isAboutView: boolean = true;
  public isHomeColor = '';
  public backroundfImg= 'https://images.ctfassets.net/ihx0a8chifpc/4Yp1F82NF8yN9gUHXMphNz/c254302efb588196d9a607832cb24e28/lorem-picsum-1280x720.jpg?w=1920&q=60&fm=webp';
 
  constructor(private authService: AuthService, private router: Router){
    
    this.router.events.subscribe(() => {
      this.isAboutView = this.router.url === '/home/about-us';
      this.isHomeColor = this.isAboutView ? 'text-white' : 'text-black';
    });
  }

  ngOnInit(): void {
    this.authService.user$.subscribe(
      (user) => {
        this.userData = user;
      }
    );
    console.log(this.userData);
  }
  onClick(){
    this.router.navigate([`/home`]);
  }
}
