import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { ToastrService } from "ngx-toastr";

@Injectable({
  providedIn: "root",
})

export class AuthService {

  constructor(
    private httpClient: HttpClient,
    private toastrService: ToastrService
  ) {}

  token = sessionStorage.getItem("token");
  productsrUrl = "http://localhost:8080/api/products";
  productsHeaders = { headers: { "Authorization": `Bearer ${this.token}` } };


}
