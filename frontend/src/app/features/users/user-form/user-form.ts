import { Component, EventEmitter, Input, Output } from '@angular/core';
import { User } from '../../../models/user';
import { UserService } from '../../../services/user-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './user-form.html',
  styleUrl: './user-form.css',
})
export class UserForm {
  @Input() user?: User;
  @Output() close = new EventEmitter<boolean>();

  model: User = {
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: ''
  };

  constructor(private userService: UserService) {}

  ngOnInit() {
    if (this.user) {
      this.model = { ...this.user };
    }
  }

  save() {
    if (this.model.id) {
      this.userService.update(this.model.id, this.model)
        .subscribe(() => this.close.emit(true));
    } else {
      this.userService.create(this.model)
        .subscribe(() => this.close.emit(true));
    }
  }

  cancel() {
    this.close.emit(false);
  }
}


