import { Injectable } from '@angular/core';
import { Router, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service'; // Importe seu serviço de autenticação
import { ToastrService } from 'ngx-toastr';

@Injectable({
    providedIn: 'root'
  })
  export class AuthGuard {
  
    constructor(private authService: AuthService, private toastr: ToastrService, private router: Router) { }
    canActivate():
      | Observable<boolean | UrlTree>
      | Promise<boolean | UrlTree>
      | boolean
      | UrlTree {
      if (!this.authService.isLoggedIn()) {
        this.toastr.info('Please Log In!');
        this.router.navigate(['/auth']);
        return false;
      }
      // logged in, so return true
      this.authService.isLoggedIn();
      return true;
    }
  }