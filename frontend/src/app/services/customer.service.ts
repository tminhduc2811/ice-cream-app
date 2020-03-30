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

  getProfileByName(username: string): Observable<Customer> {
    return this.apiService.get('/profile/customer/' + username).pipe(map(data => data));
  }

  updateProfile(obj: {}): Observable<Customer> {
    return this.apiService.put('/profile/user/', obj).pipe(map(data => data));
  }

}
