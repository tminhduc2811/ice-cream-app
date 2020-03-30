import { IceCream } from './../models/ice-cream.model';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class IceCreamService {

  constructor(private apiService: ApiService) { }

  getAll(): Observable<IceCream[]> {
    return this.apiService.get('/icecreams').pipe(map(data => data));
  }

  postQuestion(obj: {}): Observable<any> {
    return this.apiService.post('/icecreams', obj).pipe(map(data => data));
  }

  deleteQuestion(id): Observable<any> {
    return this.apiService.delete('/icecreams/' + id).pipe(map(data => data));
  }

  update(obj: {}): Observable<any> {
    return this.apiService.put('/icecreams', obj).pipe(map(data => data));
  }
}
