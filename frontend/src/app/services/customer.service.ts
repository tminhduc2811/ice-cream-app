import { HttpParams } from '@angular/common/http';
import { CustomerView } from './../auth/views/customers.view.model';
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

  getAll(query): Observable<CustomerView> {
    if (query) {
      const params = new HttpParams().set('page', query.page).set('size', query.size);
      return this.apiService.get('/customers', params).pipe(map(data => data));
    }
    return this.apiService.get('/customers').pipe(map(data => data));
  }

  getProfileByName(username: string): Observable<Customer> {
    return this.apiService.get('/profile/customer/' + username).pipe(map(data => data));
  }

  updateProfile(obj: {}): Observable<Customer> {
    return this.apiService.put('/profile/customer/', obj).pipe(map(data => data));
  }

  deleteProfile(id: number) {
    return this.apiService.delete('/customers/' + id);
  }
}
