import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'book-animation',
    pathMatch: 'full',
  },
  {
    path: 'folder/:id',
    loadComponent: () =>
      import('./folder/folder.page').then((m) => m.FolderPage),
  },
  {
    path: 'registration',
    loadComponent: () => import('./components/registration/registration.page').then( m => m.RegistrationPage)
  },
  {
    path: 'products',
    loadComponent: () => import('./components/products/products.page').then( m => m.ProductsPage)
  },
  {
    path: 'your-products',
    loadComponent: () => import('./components/your-products/your-products.page').then( m => m.YourProductsPage)
  },
  {path: 'cards/:id', loadComponent: () => import('./components/cards/cards.page').then(m => m.CardsPage)},
  {
    path: 'settings',
    loadComponent: () => import('./components/settings/settings.page').then( m => m.SettingsPage)
  },
  {
    path: 'favourites',
    loadComponent: () => import('./components/favourites/favourites.page').then( m => m.FavouritesPage)
  },
  {
    path: 'profile',
    loadComponent: () => import('./components/profile/profile.page').then( m => m.ProfilePage)
  },
  {
    path: 'book-animation',
    loadComponent: () => import('./components/book-animation/book-animation.page').then( m => m.BookAnimationPage)
  },
  {
    path: 'cart',
    loadComponent: () => import('./components/cart/cart.page').then( m => m.CartPage)
  }

];
