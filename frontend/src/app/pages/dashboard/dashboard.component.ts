import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddProductDialogComponent } from 'src/app/components/add-product-dialog/add-product-dialog.component';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ProductService } from 'src/app/services/products/products.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  constructor(
    private authService: AuthService,
    private dialog: MatDialog,
    private productsService: ProductService
  ) {}

  ngOnInit(): void {
    this.authService.retrieveProfile();
    this.productsService.products$.subscribe();
  }

  openDialog(){
    const dialogRef = this.dialog.open(AddProductDialogComponent, {
      width: '285px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result !== undefined){
        this.productsService.products$ = result;
      }
    });
  }

  logout(){
    this.authService.logoutUser();
  }
}
