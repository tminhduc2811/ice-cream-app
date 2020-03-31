import { PageService } from './../../services/page.service';
import { Page } from './../../models/page.model';
import { OrderView } from './../../auth/views/orders.view.model';
import { Order } from './../../models/order.model';
import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  orders: Order[] = [];
  isLoaded = false;
  isLoading = false;
  result: OrderView;
  page: Page;
  size = 5;

  constructor(private orderService: OrderService, private pageService: PageService) { }

  ngOnInit(): void {
    this.isLoading = true;
    this.orderService.getAll({ page: 0, size: this.size }).subscribe(
      rs => {
        this.result = rs;
        this.orders = this.result.content;
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
    this.orderService.getAll({ page: currentPage - 1, size: this.size })
      .subscribe(rs => {
        this.result = rs;
        this.orders = this.result.content;
        this.isLoading = false;
      }, err => {
        this.isLoading = false;
      });

  }
}
