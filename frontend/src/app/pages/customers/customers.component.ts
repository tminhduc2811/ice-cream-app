import { Router } from '@angular/router';
import { debounceTime } from 'rxjs/operators';
import { PageService } from './../../services/page.service';
import { Page } from './../../models/page.model';
import { CustomerView } from './../../auth/views/customers.view.model';
import { CustomerService } from './../../services/customer.service';
import { Customer } from './../../models/customer.model';
import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  customers: Customer[] = [];
  customersLoaded = false;
  isLoading = false;
  result: CustomerView;
  page: Page;
  size = 5;
  // Alert messages
  successMessage = '';
  success = new Subject<string>();
  warningMessage = '';
  warning = new Subject<string>();

  constructor(private customerService: CustomerService, private pageService: PageService, private router: Router) {

  }

  ngOnInit(): void {
    this.setAlert();
    this.isLoading = true;
    this.customerService.getAll({ page: 0, size: this.size }).subscribe(
      rs => {
        this.result = rs;
        this.customers = this.result.content;
        this.setPage(1);
        this.customersLoaded = true;
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
    this.customerService.getAll({ page: currentPage - 1, size: this.size })
      .subscribe(rs => {
        this.result = rs;
        this.customers = this.result.content;
        this.isLoading = false;
      }, err => {
        this.isLoading = false;
      });
  }

  editOnClick(index) {
    this.router.navigate(['customer/', this.customers[index].userName]);
  }

  deleteOnClick(index) {
    this.customerService.deleteProfile(this.customers[index].id)
      .subscribe(rs => {
        this.customers.splice(index, 1);
      }, err => {
        this.warning.next(err.message);
      });
  }
}
