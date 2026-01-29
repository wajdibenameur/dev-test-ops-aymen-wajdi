import { Component } from '@angular/core';
import { Product } from '../../../models/product';
import { ProductService } from '../../../services/product-service';
import { ProductForm } from '../product-form/product-form';
import { CommonModule, CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-product-list',
  standalone:true,
  imports: [ProductForm,CommonModule],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css',
})
export class ProductList {

  products: Product[] = [];
  selectedProduct?: Product;
  showForm = false;

  constructor(private service: ProductService) {
    this.load();
  }

  load() {
    this.service.getAll().subscribe(data => this.products = data);
  }

  add() {
    this.selectedProduct = undefined;
    this.showForm = true;
  }

  edit(p: Product) {
    this.selectedProduct = p;
    this.showForm = true;
  }

  delete(id: number) {
    this.service.delete(id).subscribe(() => this.load());
  }

  close(refresh: boolean) {
    this.showForm = false;
    if (refresh) this.load();
  }
}


