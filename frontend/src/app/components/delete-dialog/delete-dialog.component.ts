import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import iProduct from 'src/app/interfaces/products.interface';
import { ProductService } from 'src/app/services/products/products.service';

@Component({
  selector: 'app-delete-dialog',
  templateUrl: './delete-dialog.component.html',
  styleUrls: ['./delete-dialog.component.css']
})
export class DeleteDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<DeleteDialogComponent>,
    private productsService: ProductService,
    @Inject(MAT_DIALOG_DATA) public data: iProduct
  ) {}

  product = { ...this.data }
  id = this.product.id;

  confirmDelete(): void {
    this.dialogRef.close(this.productsService.deleteProduct(this.product.id));
  }

  cancel(): void {
    this.dialogRef.close();
  }
}
