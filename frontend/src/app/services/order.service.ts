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

  getAll(): Observable<[Order]> {
    return this.apiService.get('/orders').pipe(map(data => data));
  }
}
