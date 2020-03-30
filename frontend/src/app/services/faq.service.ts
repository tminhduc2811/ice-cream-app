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

  postQuestion(obj: {}): Observable<any> {
    return this.apiService.post('/faq', obj).pipe(map(data => data));
  }

  deleteQuestion(id): Observable<any> {
    return this.apiService.delete('/faq/' + id).pipe(map(data => data));
  }

  update(obj: {}): Observable<any> {
    return this.apiService.put('/faq', obj).pipe(map(data => data));
  }
}
