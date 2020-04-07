import { CheckoutModel } from './../models/checkout.model';

import { HttpParams } from '@angular/common/http';
import { OrderView } from './../auth/views/orders.view.model';
import { Order } from './../models/order.model';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Injectable, EventEmitter } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private apiService: ApiService) { }

  getAll(query): Observable<OrderView> {
    if (query) {
      const params = new HttpParams().set('page', query.page).set('size', query.size);
      return this.apiService.get('/orders', params).pipe(map(data => data));
    }
    return this.apiService.get('/orders').pipe(map(data => data));
  }

  createOrder(checkoutModel: CheckoutModel): Observable<string> {
    return this.apiService.post('/checkout', checkoutModel).pipe(map(data => data));
  }

  deleteOrder(id: number): Observable<any> {
    return this.apiService.delete('/orders/' + id).pipe(map(data => data));
  }

  updateOrder(order: Order): Observable<any> {
    return this.apiService.put('/orders', order).pipe(map(data => data));
  }

}
