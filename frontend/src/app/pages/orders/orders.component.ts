import { debounceTime } from 'rxjs/operators';
import { PageService } from './../../services/page.service';
import { Page } from './../../models/page.model';
import { OrderView } from './../../auth/views/orders.view.model';
import { Order } from './../../models/order.model';
import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';
import { Subject } from 'rxjs';

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
  // Alert messages
  successMessage = '';
  success = new Subject<string>();
  warningMessage = '';
  warning = new Subject<string>();

  constructor(private orderService: OrderService, private pageService: PageService) { }

  ngOnInit(): void {
    this.setAlert();
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
    this.orderService.getAll({ page: currentPage - 1, size: this.size })
      .subscribe(rs => {
        this.result = rs;
        this.orders = this.result.content;
        this.isLoading = false;
      }, err => {
        this.isLoading = false;
      });
  }

  editOnClick(index) {

  }

  deleteOnClick(index) {
    this.orderService.deleteOrder(this.orders[index].id)
    .subscribe(rs => {
      this.setPage(this.page.currentPage);
      this.success.next('Order has been deleted successfully.');
    }, err => {
      this.warning.next(err.message);
    });
  }
}
