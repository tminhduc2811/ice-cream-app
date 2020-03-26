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

  constructor(private customerService: CustomerService) {
    this.customerService.getAll().subscribe(
      customers => {
        this.customers = customers;
        this.customersLoaded = true;
      }
    );
  }

  ngOnInit(): void {
  }

}
