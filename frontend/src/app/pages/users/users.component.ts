import { Router } from '@angular/router';
import { Role } from './../../models/role.model';
import { PageService } from './../../services/page.service';
import { UserView } from './../../auth/views/users.view.model';
import { Page } from './../../models/page.model';
import { CustomerView } from './../../auth/views/customers.view.model';
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
  isLoaded = false;
  isLoading = false;
  result: UserView;
  page: Page;
  size = 5;

  constructor(private userService: UserService, private pageService: PageService, private router: Router) { }

  ngOnInit(): void {
    this.isLoading = true;
    this.userService.getAll({ page: 0, size: this.size }).subscribe(
      rs => {
        this.result = rs;
        this.users = this.result.content;
        this.setPage(1);
        this.isLoaded = true;
        this.isLoading = false;
      }
    );
  }

  setPage(currentPage: number) {
    if (currentPage < 0 || currentPage >= this.result.totalPages + 1) {
      return;
    }
    this.page = this.pageService.getPage(this.result.totalPages, currentPage);
    this.isLoading = true;
    // get new recipes
    this.userService.getAll({ page: currentPage - 1, size: this.size })
      .subscribe(rs => {
        this.result = rs;
        this.users = this.result.content;
        this.isLoading = false;
      }, err => {
        this.isLoading = false;
      });
  }

  editOnClick(index) {
    console.log(index);
    this.router.navigate(['user/', this.users[index].userName]);
  }
}
