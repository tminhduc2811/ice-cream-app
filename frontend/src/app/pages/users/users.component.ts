import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from './../../models/user.model';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[] = [];
  usersLoaded = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getAll().subscribe(
      users => {
        this.users = users;
        this.usersLoaded = true;
      }
    );
  }

}
