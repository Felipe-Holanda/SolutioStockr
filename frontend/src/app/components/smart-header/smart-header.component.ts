import { Component, Inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-smart-header',
  templateUrl: './smart-header.component.html',
  styleUrls: ['./smart-header.component.css']
})
export class SmartHeaderComponent {
  
  private router: Router;
  

  constructor(router: Router) {
    this.router = router;
  }

  static nome = "Nome do usuário";

  logout(){
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

}
