import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { ToastrService } from "ngx-toastr";
import { BehaviorSubject, Observable } from "rxjs";
import iProduct from "src/app/interfaces/products.interface";

@Injectable({
  providedIn: "root",
})
export class ProductService {

  productsrUrl = "http://localhost:8080/api/products";
  productsHeaders = { headers: { "Authorization": `Bearer ${sessionStorage.getItem("token")}` } };
  private productsSubject = new BehaviorSubject<iProduct[]>([]);
  products$: Observable<iProduct[]> = this.productsSubject.asObservable();

  constructor(
    private httpClient: HttpClient,
    private toastrService: ToastrService
  ) {}

  private get products(): iProduct[] {
    return this.productsSubject.value;
  }

  private set products(value: iProduct[]) {
    this.productsSubject.next(value);
  }

  private getProductUrl(id: string): string {
    return `${this.productsrUrl}/${id}`;
  }

  private parseRegistration(registration: string): string {
    const cleanedCNPJ = registration.replace(/\D/g, '');

    if (cleanedCNPJ.length !== 14) {
      throw new Error('O CNPJ deve ter 14 dígitos.');
    }

    const formattedCNPJ = `${cleanedCNPJ.slice(0, 2)}.${cleanedCNPJ.slice(2, 5)}.${cleanedCNPJ.slice(5, 8)}/${cleanedCNPJ.slice(8, 12)}-${cleanedCNPJ.slice(12)}`;

    return formattedCNPJ;
  }

  private parseValue(value: string): string {
    return value.split(',').join('.');
  }

  ngOnInit(){
    this.listProducts();
  }

  listProducts(): void {
    this.httpClient
      .get<iProduct[]>(this.productsrUrl, this.productsHeaders)
      .subscribe(
        (products) => {
          this.products = products;
        },
        (error) => {
          this.toastrService.error("Ocorreu um erro ao buscar os produtos!");
        }
      );
  }

  createProduct(product: iProduct): void {
    product.price = this.parseValue(product.price);
    product.vendorRegistration = this.parseRegistration(product.vendorRegistration);

    this.httpClient
      .post<iProduct[]>(this.productsrUrl, product, this.productsHeaders)
      .subscribe(
        () => {
          this.toastrService.success('Produto criado com sucesso!');
          this.listProducts();
        },
        (error) => {
          this.toastrService.error(`${error.error.message}`, 'Erro ao criar produto!');
        }
      );
  }

  updateProduct(product: iProduct): void {
    if (product.vendorRegistration !== undefined) {
      product.vendorRegistration = this.parseRegistration(product.vendorRegistration);
    }

    if (product.price !== undefined) {
      product.price = this.parseValue(product.price);
    }

    this.httpClient
      .patch<iProduct>(this.getProductUrl(product.id), product, this.productsHeaders)
      .subscribe(
        () => {
          const updatedProducts = this.products.map((p) => (p.id === product.id ? product : p));
          this.products = updatedProducts;
          this.toastrService.success('Produto atualizado com sucesso!');
        },
        (error) => {
          this.toastrService.error('Ocorreu um erro ao atualizar o produto!');
        }
      );
  }

  increaseProduct(id: string, amountDisposable: string): void {
    this.httpClient
      .put<iProduct>(this.getProductUrl(id), { amountDisposable }, this.productsHeaders)
      .subscribe(
        (response) => {
          const updatedProduct = response;
          const updatedProducts = this.products.map((p) => (p.id === updatedProduct.id ? updatedProduct : p));
          this.products = updatedProducts;
          this.toastrService.success('Estoque de produto incrementado com sucesso!');
        },
        (error) => {
          this.toastrService.error('Ocorreu um erro ao incrementar estoque do produto!');
        }
      );
  }

  deleteProduct(id: string): void {
    this.httpClient
      .delete(this.getProductUrl(id), this.productsHeaders)
      .subscribe(
        () => {
          const updatedProducts = this.products.filter((p) => p.id !== id);
          this.products = updatedProducts;
          this.toastrService.success('Produto deletado com sucesso!');
        }
      );
  }
}
