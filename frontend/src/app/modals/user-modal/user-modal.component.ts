import { User } from './../../models/user.model';
import { NgForm } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-modal',
  templateUrl: './user-modal.component.html',
  styleUrls: ['./user-modal.component.css']
})
export class UserModalComponent implements OnInit {

  constructor(public modal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  confirm(form: NgForm) {
    const user: User = {
      id: null,
      userName: form.value.username,
      password: form.value.password,
      email: form.value.email,
      avatar: null,
      fullName: null,
      roles: [],
      status: 1,
    };
    this.modal.close(user);
  }
}
