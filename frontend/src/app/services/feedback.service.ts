import { HttpParams } from '@angular/common/http';
import { Feedback } from './../models/feedback.model';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { FeedbackView } from '../auth/views/feedback.view.model';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  constructor(private apiService: ApiService) { }
  getAll(query): Observable<FeedbackView> {
    if (query) {
      const params = new HttpParams().set('page', query.page).set('size', query.size);
      return this.apiService.get('/feedback', params).pipe(map(data => data));
    }
    return this.apiService.get('/feedback').pipe(map(data => data));
  }

  delete(id: number) {
    return this.apiService.delete('/feedback/' + id).pipe(map(data => data));
  }
}
