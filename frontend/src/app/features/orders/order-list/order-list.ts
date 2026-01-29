import { Component } from '@angular/core';
import { Commande } from '../../../models/commande';
import { CommandeService } from '../../../services/commande-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-order-list',
  standalone:true,
  imports: [CommonModule],
  templateUrl: './order-list.html',
  styleUrl: './order-list.css',
})
export class OrderList {

  orders: Commande[] = [];

  constructor(private service: CommandeService) {
    this.load();
  }

  load() {
    this.service.getAll().subscribe(data => this.orders = data);
  }

  delete(id: number) {
    this.service.delete(id).subscribe(() => this.load());
  }
}


