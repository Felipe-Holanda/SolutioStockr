import {Component} from '@angular/core';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})


export class RegisterComponent {

  hide = true;

  formData = {
    name: '',
    login: '',
    password: '',
    registration: ''
  }

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
      this.authService.checkIsLogged();
    }

  registerUser(){
    this.authService.registerUser(
      this.formData.name,
      this.formData.login,
      this.formData.registration,
      this.formData.password
    );
  }

  isValid(): boolean {
    return (this.formData.name !== ''
    && this.formData.login !== ''
    && this.formData.password !== ''
    && this.formData.registration !== '');
  }



}
