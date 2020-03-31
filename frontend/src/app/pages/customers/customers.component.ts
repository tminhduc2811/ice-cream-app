import { PageService } from './../../services/page.service';
import { Page } from './../../models/page.model';
import { CustomerView } from './../../auth/views/customers.view.model';
import { CustomerService } from './../../services/customer.service';
import { Customer } from './../../models/customer.model';
import { Component, OnInit } from '@angular/core';

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
  constructor(private customerService: CustomerService, private pageService: PageService) {

  }

  ngOnInit(): void {
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
}
