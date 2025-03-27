import { Injectable} from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { map, Observable, tap } from 'rxjs';
import { ProveService } from './sesion.service';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate{
  
  constructor(private proveService: ProveService, private router: Router){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.proveService.userData$.pipe(
      map((data: any) => data?.username),
      tap((isAuthenticated) => {
        if (!isAuthenticated) {
          this.router.navigate(['/home']);
        }
      })
    );
    
    
  }
}