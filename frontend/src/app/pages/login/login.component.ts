import { Component } from '@angular/core';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  hide = true;

  formData = {
    login: '',
    password: '',
  }

  constructor(
    private authService: AuthService,
    ) {}

  isValid(): boolean {
    return this.formData.login !== '' && this.formData.password !== '';
  }

  ngOnInit(): void {
    this.authService.checkIsLogged();
  }

  loginUser(){
    this.authService.loginUser(this.formData.login, this.formData.password);
  }
}
