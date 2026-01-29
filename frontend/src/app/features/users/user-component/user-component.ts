import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { UserForm } from '../user-form/user-form';
import { UserList } from '../user-list/user-list';

@Component({
  selector: 'app-user-component',
  standalone:true,
  imports: [CommonModule,UserForm,UserList],
  templateUrl: './user-component.html',
  styleUrl: './user-component.css',
})
export class UserComponent {
showForm = false;

  toggleForm() {
    this.showForm = !this.showForm;
  }
}
