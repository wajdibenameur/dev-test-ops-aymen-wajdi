import { Routes } from '@angular/router';
import { UserList } from './features/users/user-list/user-list';
import { ProductList } from './features/products/product-list/product-list';
import { OrderList } from './features/orders/order-list/order-list';
import { HomeComponent } from './home-component/home-component';
import { UserComponent } from './features/users/user-component/user-component';
import { ProductComponent } from './features/products/product-component/product-component';
import { OrdersComponent } from './features/orders/orders-component/orders-component';

export const routes: Routes = [
    { path: '', component: HomeComponent },       // Page d'accueil
  { path: 'users', component: UserComponent },  // Page CRUD Users
  { path: 'products', component: ProductComponent },
  { path: 'orders', component: OrdersComponent },
  { path: '**', redirectTo: '' }                // fallback
];
