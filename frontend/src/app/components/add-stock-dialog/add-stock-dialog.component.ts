import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import iProduct from 'src/app/interfaces/products.interface';
import { ProductService } from 'src/app/services/products/products.service';

@Component({
  selector: 'app-add-stock-dialog',
  templateUrl: './add-stock-dialog.component.html',
})

export class AddStockDialogComponent {

  product: iProduct = {...this.data, amountDisposable: ''}

  constructor(
    public dialogRef: MatDialogRef<AddStockDialogComponent>,
    private productsService: ProductService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }


  save(): void {
    this.dialogRef.close(this.productsService.increaseProduct(this.product.id, this.product.amountDisposable));
  }

  cancel(): void {
    this.dialogRef.close();
  }
}
