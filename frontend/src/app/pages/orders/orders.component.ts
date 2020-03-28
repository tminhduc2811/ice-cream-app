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

  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
    this.isLoading = true;
    this.orderService.getAll().subscribe(
      orders => {
        this.orders = orders;
        this.isLoaded = true;
        this.isLoading = false;
      }
    );
  }

}
