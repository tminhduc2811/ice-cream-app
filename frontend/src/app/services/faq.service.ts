import { FAQ } from './../models/faq.model';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FaqService {

  constructor(private apiService: ApiService) { }

  getAll(): Observable<[FAQ]> {
    return this.apiService.get('/faq').pipe(map(data => data));
  }
}
