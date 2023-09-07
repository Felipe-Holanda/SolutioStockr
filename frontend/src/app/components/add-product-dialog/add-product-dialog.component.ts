import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProductService } from 'src/app/services/products/products.service';

@Component({
  selector: 'app-add-product-dialog',
  templateUrl: './add-product-dialog.component.html',
})

export class AddProductDialogComponent {
  product = {
    id:'',
    productName: '',
    price: '',
    amountDisposable: '',
    vendorName: '',
    vendorRegistration: ''
  };

  constructor(
    public dialogRef: MatDialogRef<AddProductDialogComponent>,
    private productsService: ProductService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  cancel(): void {
    this.dialogRef.close();
  }

  save(): void {
    this.dialogRef.close(this.productsService.createProduct(this.product));
  }
}
