import { Customer } from './../models/customer.model';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private apiService: ApiService) { }

  getAll(): Observable<[Customer]> {
    return this.apiService.get('/customers').pipe(map(data => data));
  }
}
