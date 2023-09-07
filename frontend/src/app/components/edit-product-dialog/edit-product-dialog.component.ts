import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProductService } from 'src/app/services/products/products.service';

@Component({
  selector: 'app-edit-product-dialog',
  templateUrl: './edit-product-dialog.component.html',
})

export class EditProductModalComponent {

  product = {
    id: '',
    productName: '',
    price: '',
    amountDisposable: '',
    vendorName: '',
    vendorRegistration: ''
  }

  constructor(
    public dialogRef: MatDialogRef<EditProductModalComponent>,
    private productsService: ProductService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.product = { ...data };
  }

  cancel(): void {
    this.dialogRef.close();
  }

  save(): void {
    this.dialogRef.close(this.productsService.updateProduct(this.product));
  }
}
