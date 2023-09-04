import { Inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private token: string = '';

  constructor(@Inject("token") token: string){
    this.token = token;
  }

  public isLoggedIn(): boolean {
    return this.token !== '';
  }

  getToken(){
    return this.token;
  }

  setToken(token: string){
    this.token = token;
  }
}
