import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable, of } from 'rxjs'; // Importe 'of' do RxJS
import { HttpClient } from '@angular/common/http';
import { Router } from "@angular/router";
import { ToastrService } from 'ngx-toastr';
import { catchError, map } from 'rxjs/operators';
import { AuthService } from 'src/app/services/auth/auth.service';
import iUser from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private authService: AuthService,
    private toastrService: ToastrService,
    private httpClient: HttpClient,
    private router: Router
  ){}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      const token = sessionStorage.getItem('token');

      if (!token) {
        // Redirecionar para a rota raiz se não houver token
        this.toastrService.error('Você não está autenticado.', 'Erro de autenticação');
        return this.router.createUrlTree(['/']);
      }

      this.httpClient.get('http://localhost:8080/api/auth/profile', { headers: { 'Authorization': `Bearer ${token}` }})
        .subscribe((response)=>{
          const user = response as iUser;
          this.toastrService.success(`Bem vindo de volta, ${user.name}!`);
          this.authService.setToken(token);
          return this.router.createUrlTree(['/dashboard']);
        }, (error)=>{
          this.toastrService.error('Você não está autenticado.', 'Erro de autenticação');
          sessionStorage.clear();
          return this.router.createUrlTree(['/']);
        })

      return true;
  }
}
