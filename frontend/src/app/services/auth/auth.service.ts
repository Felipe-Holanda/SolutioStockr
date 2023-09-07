import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { Router } from "@angular/router";
import iUser from 'src/app/interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiUrl = 'http://localhost:8080/api/auth';
  token = '';
  user = {
    id: '',
    name: '',
    username: '',
    registration: '',
  }

  constructor(
    private httpClient: HttpClient,
    private toastrService: ToastrService,
    private router: Router
  ) {}

  ngOnInit() {
    const token = sessionStorage.getItem('token');
    if(token !== null && token !== ''){
      this.token = token;
    }
  }

  loginUser(login: string, password: string) {
    this.httpClient.post(this.apiUrl + '/login', { login, password })
      .subscribe((response: any) => {
        sessionStorage.setItem('token', response.token);
        this.router.navigate(['/dashboard']);
      }, (error)=>{
        this.toastrService.error('Usuário ou senha incorretos!');
      });
  }

  registerUser(name: string, login: string, registration:string, password: string) {
    this.httpClient.post(this.apiUrl + '/register', { name, login, registration, password })
      .subscribe((response: any) => {
        this.toastrService.success('Usuário cadastrado com sucesso!');
        this.router.navigate(['/']);
      },
      (error) => {
        this.toastrService.error(`${error.error.message}`, 'Erro ao cadastrar usuário!')
      });
  }

  retrieveProfile() {
      this.httpClient.get(this.apiUrl + '/profile', { headers: { 'Authorization': `Bearer ${this.token}` }})
      .subscribe((response: any) => {
        this.toastrService.success(`Bem vindo de volta, ${response.name}!`);
        this.user = response;
      });
  }

  checkIsLogged(): void{

    const actualPage = this.router.url;

    this.httpClient.get(this.apiUrl + '/profile', { headers: { 'Authorization': `Bearer ${sessionStorage.getItem('token')}` }})
      .subscribe((response) => {
        if(actualPage === '/' || actualPage === '/register'){
          this.router.navigate(['/dashboard']);
        }},
        (error) => {
          if(actualPage !== '/' && actualPage !== '/register'){
            this.router.navigate(['/']);
            if(this.token) localStorage.removeItem('token');
          }
        });
  }

  logoutUser(){
    sessionStorage.removeItem('token');
    this.token = '';
    this.router.navigate(['/']);
    this.toastrService.info('Tchau! Volte logo!')
  }

  //Getters e Setters:

  getToken(): string {
    return this.token;
  }

  setToken(token: string): void {
    this.token = token;
  }

  getUser(): iUser {
    return this.user;
  }

  setUser(user: iUser): void {
    this.user = user;
  }

}
