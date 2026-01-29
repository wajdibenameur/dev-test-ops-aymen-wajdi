import { Component } from '@angular/core';
import { User } from '../../../models/user';
import { UserService } from '../../../services/user-service';
import { CommonModule } from '@angular/common';
import { UserForm } from '../user-form/user-form';

@Component({
  selector: 'app-user-list',
  standalone:true,
  imports: [CommonModule,UserForm],
  templateUrl: './user-list.html',
  styleUrl: './user-list.css',
})
export class UserList {




  users: User[] = [];
  selectedUser?: User;
  showForm = false;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getAll().subscribe(data => this.users = data);
  }

  addUser() {
    this.selectedUser = undefined;
    this.showForm = true;
  }

  editUser(user: User) {
    this.selectedUser = user;
    this.showForm = true;
  }

  deleteUser(id: number) {
    if (confirm('Delete this user ?')) {
      this.userService.delete(id).subscribe(() => this.loadUsers());
    }
  }

  onFormClose(refresh: boolean) {
    this.showForm = false;
    if (refresh) this.loadUsers();
  }
}


