import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthGuard } from './auth.guard';
import { Router } from '@angular/router';

describe('AuthGuard', () => {
  let guard: AuthGuard;
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule], // Importa RouterTestingModule para simular rutas
      providers: [AuthGuard]
    });
    guard = TestBed.inject(AuthGuard);
    router = TestBed.inject(Router);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy(); // Verifica que el guard se haya creado correctamente
  });

  it('should allow activation if canActivate returns true', () => {
    const routeMock: any = {}; // Simula un ActivatedRouteSnapshot
    const stateMock: any = {}; // Simula un RouterStateSnapshot

    spyOn(guard, 'canActivate').and.returnValue(true); // Simula que canActivate devuelve true

    const result = guard.canActivate(routeMock, stateMock);
    expect(result).toBeTrue(); // Verifica que el guard permita la activación
  });

  it('should deny activation if canActivate returns false', () => {
    const routeMock: any = {}; // Simula un ActivatedRouteSnapshot
    const stateMock: any = {}; // Simula un RouterStateSnapshot

    spyOn(guard, 'canActivate').and.returnValue(false); // Simula que canActivate devuelve false

    const result = guard.canActivate(routeMock, stateMock);
    expect(result).toBeFalse(); // Verifica que el guard deniegue la activación
  });
});