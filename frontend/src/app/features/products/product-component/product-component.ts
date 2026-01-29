import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ProductList } from '../product-list/product-list';
import { ProductForm } from '../product-form/product-form';

@Component({
  selector: 'app-product-component',
  standalone:true,
  imports: [CommonModule, ProductList, ProductForm],
  templateUrl: './product-component.html',
  styleUrl: './product-component.css',
})
export class ProductComponent {
showForm = false;

  toggleForm() {
    this.showForm = !this.showForm;
  }
}
