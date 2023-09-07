import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EditProductModalComponent } from '../edit-product-dialog/edit-product-dialog.component';
import iProduct from 'src/app/interfaces/products.interface';
import { ProductService } from 'src/app/services/products/products.service';
import { AddStockDialogComponent } from '../add-stock-dialog/add-stock-dialog.component';
import { DeleteDialogComponent } from '../delete-dialog/delete-dialog.component';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  displayedColumns: string[] = ['productName', 'price', 'amountDisposable', 'vendorName', 'vendorRegistration', 'actions'];

  constructor(
    public productsService: ProductService,
    private dialog: MatDialog
  ){}
  ngOnInit(): void {
    this.productsService.products$.subscribe();
  }

    editProduct(product: iProduct): void {
      const dialogRef = this.dialog.open(EditProductModalComponent, {
        data: product,
        width: '285px'
      });

      dialogRef.afterClosed().subscribe(result => {
        if(result !== undefined){
          this.productsService.products$ = result;
        }
      });

    }

  addToStock(product: string) {
    const dialogRef = this.dialog.open(AddStockDialogComponent, {
      data: product,
      width: '285px'
    })

    dialogRef.afterClosed().subscribe(result => {
      if(result !== undefined){
        this.productsService.products$ = result;
      }
    });

  }

  deleteProduct(productID: string) {
    this.productsService.deleteProduct(productID);

    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      data: productID,
      width: '285px'
    })

    dialogRef.afterClosed().subscribe(result => {
      if(result !== undefined){
        this.productsService.products$ = result;
      }
    });
  }
}
