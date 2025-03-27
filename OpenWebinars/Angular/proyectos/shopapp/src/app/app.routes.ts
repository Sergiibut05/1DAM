import { Routes } from '@angular/router';
import { InitialComponent } from './components/initial/initial.component';
import { AuthGuard } from './services/auth.guard';


export const routes: Routes = [
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'home', component: InitialComponent, children: [
        {path: 'about-us', loadChildren: () => import('./components/home-about-us/home-about-us.module').then(
            m => m.HomeAboutUsModule)},
        {path: 'products', loadChildren: () => import('./components/home-products/home-products.module').then(
            m => m.HomeProductsModule)},
    ]  
    },
    {path: 'cart', loadChildren: () => import('./components/cart/cart.module').then(
        m => m.CartModule), canActivate: [AuthGuard]}
];
