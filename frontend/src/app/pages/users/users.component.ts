import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmModalComponent } from './../../modals/confirm-modal/confirm-modal.component';
import { ConfirmModalModel } from './../../modals/confirm-modal/confirm-modal.model';
import { AuthService } from './../../services/auth.service';
import { debounceTime } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Role } from './../../models/role.model';
import { PageService } from './../../services/page.service';
import { UserView } from './../../auth/views/users.view.model';
import { Page } from './../../models/page.model';
import { CustomerView } from './../../auth/views/customers.view.model';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from './../../models/user.model';
import { Subject } from 'rxjs';
import { UserModalComponent } from 'src/app/modals/user-modal/user-modal.component';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[] = [];
  isLoaded = false;
  isLoading = false;
  isAdmin = false;
  result: UserView;
  page: Page;
  size = 5;
  // Alert messages
  successMessage = '';
  success = new Subject<string>();
  warningMessage = '';
  warning = new Subject<string>();

  constructor(private userService: UserService,
              private pageService: PageService,
              private router: Router,
              private auth: AuthService,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.isAdmin = this.auth.isAdmin();
    this.setAlert();
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

  setAlert() {

    // Set timeout for alert
    this.success.subscribe(message => this.successMessage = message);
    this.success.pipe(debounceTime(3000)).subscribe(() => this.successMessage = '');
    this.warning.subscribe(message => this.warningMessage = message);
    this.warning.pipe(debounceTime(4000)).subscribe(() => this.warningMessage = '');
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
    this.router.navigate(['user/', this.users[index].userName]);
  }

  deleteOnClick(index) {

    const modalRef = this.modalService.open(ConfirmModalComponent);
    modalRef.componentInstance.data = {header: 'Are you sure to delete user: ' + this.users[index].userName,
                                      message: 'All information which is related to this customer will also be deleted',
                                      subMessage: 'This operation will not be undoned',
                                      danger: true};
    modalRef.result.then(rs => {
      this.userService.deleteProfile(this.users[index].id)
    .subscribe(() => {
      this.users.splice(index, 1);
    }, err => {
      this.warning.next(err.message);
    });
    }). catch(rs => {});
  }

  newUser() {
    const modalRef = this.modalService.open(UserModalComponent, {size: 'm'});
    modalRef.result.then(rs => {
      this.userService.createUser(rs)
      .subscribe(res => {
        this.success.next('Created user successfully');
        this.setPage(this.page.currentPage);
      }, err => {
        this.warning.next(err.message);
      });
    }).catch(rs => {});
  }
}
