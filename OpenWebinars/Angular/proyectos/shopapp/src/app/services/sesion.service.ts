import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProveService {
  private userData: BehaviorSubject<object> = new BehaviorSubject({});

  constructor() { }

  public userData$: Observable<any> = this.userData.asObservable();

  setUserData(data: any): void {
    this.userData.next(data);
  }
  
  
}
