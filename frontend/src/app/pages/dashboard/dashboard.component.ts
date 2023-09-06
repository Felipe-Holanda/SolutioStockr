import { Component } from '@angular/core';
import { AuthService } from 'src/app/services/auth/auth.service';
import { Store } from '@ngrx/store';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  constructor(
    private authService: AuthService,
    private store: Store
  ) {}

  logout(){
    this.authService.logoutUser();
  }
}
