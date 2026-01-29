import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { User } from '../../../models/user';
import { Product } from '../../../models/product';
import { CreateCommandeRequest } from '../../../models/createCommandeDTO';

import { UserService } from '../../../services/user-service';
import { ProductService } from '../../../services/product-service';
import { CommandeService } from '../../../services/commande-service';

@Component({
  selector: 'app-order-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './order-form.html'
})
export class OrderForm {

  users: User[] = [];
  products: Product[] = [];

  selectedUserId?: number;
  selectedProductsId: number[] = [];

  constructor(
    private userService: UserService,
    private productService: ProductService,
    private orderService: CommandeService
  ) {}

  ngOnInit(): void {
    this.userService.getAll().subscribe(data => this.users = data);
    this.productService.getAll().subscribe(data => this.products = data);
  }

  onProductSelect(event: Event) {
    const select = event.target as HTMLSelectElement;
    this.selectedProductsId = Array.from(select.selectedOptions)
      .map(option => Number(option.value));
  }

  save() {
    if (!this.selectedUserId || this.selectedProductsId.length === 0) {
      alert('Veuillez sélectionner un client et au moins un produit');
      return;
    }

    const request: CreateCommandeRequest = {
      userId: this.selectedUserId,
      productsId: this.selectedProductsId
    };

    this.orderService.create(request).subscribe(() => {
      alert('Commande créée avec succès');
    });
  }
}

