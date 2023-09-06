import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Store } from '@ngrx/store';
import { MatDialog } from '@angular/material/dialog';
import { EditProductModalComponent } from '../edit-product-dialog/edit-product-dialog.component';
import { updateProduct, deleteProduct } from '../../states/product/product.actions';
import { selectAllProducts } from '../../states/product/product.selectors';
import iProduct from 'src/app/interfaces/products.interface';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  displayedColumns: string[] = ['productName', 'price', 'amountDisposable', 'vendorName', 'vendorRegistration', 'actions'];
  products$ = this.store.select(selectAllProducts);

  constructor(
    private store: Store<{ products: iProduct[] }>,
    private dialog: MatDialog
  ){}
  ngOnInit(): void {
    this.products$ = this.store.select(selectAllProducts);
  }

  editProduct(product: iProduct) {
    const dialogRef = this.dialog.open(EditProductModalComponent, {
      data: product,
      width: '400px'
    });
  }

  addToStock(productID: string) {
    // Implemente a lógica para adicionar ao estoque aqui
  }

  deleteProduct(productID: string) {
    this.store.dispatch(deleteProduct({ productId: productID }));
  }
}
