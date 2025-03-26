import { Routes } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { CharacterComponent } from './components/character/character.component';

export const routes: Routes = [
    { path: '', loadComponent: () => import("./components/header/header.component").then(c => c.HeaderComponent),title: "landing page"
    },
    { path: 'main', loadComponent: () => import("./components/character/character.component").then(c => c.CharacterComponent),title: "main"
    }
];
