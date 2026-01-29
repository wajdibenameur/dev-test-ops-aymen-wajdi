import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Product } from '../../../models/product';
import { ProductService } from '../../../services/product-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-product-form',
  standalone:true,
  imports: [FormsModule],
  templateUrl: './product-form.html',
  styleUrl: './product-form.css',
})
export class ProductForm {

  @Input() product?: Product;
  @Output() close = new EventEmitter<boolean>();

  model: Product = {
    nameProduct: '',
    price: 0,
    quantity: 0
  };

  constructor(private service: ProductService) {}

  ngOnInit() {
    if (this.product) this.model = { ...this.product };
  }

  save() {
    if (this.model.id) {
      this.service.update(this.model.id, this.model)
        .subscribe(() => this.close.emit(true));
    } else {
      this.service.create(this.model)
        .subscribe(() => this.close.emit(true));
    }
  }
   cancel() {
    this.close.emit(false); // ferme le formulaire sans refresh
  }
}


