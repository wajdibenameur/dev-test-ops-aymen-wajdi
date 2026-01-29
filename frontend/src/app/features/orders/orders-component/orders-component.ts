import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { OrderList } from '../order-list/order-list';
import { OrderForm } from '../order-form/order-form';

@Component({
  selector: 'app-orders-component',
  standalone:true,
  imports: [CommonModule, OrderList, OrderForm],
  templateUrl: './orders-component.html',
  styleUrl: './orders-component.css',
})
export class OrdersComponent {
showForm = false;

  toggleForm() {
    this.showForm = !this.showForm;
  }
}
