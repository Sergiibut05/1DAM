import { NgModule } from '@angular/core';
import { AboutUsComponent } from '../about-us/about-us.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild([{path: '', component: AboutUsComponent}])
  ]
})
export class HomeAboutUsModule { }
