import { Feedback } from './../models/feedback.model';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  constructor(private apiService: ApiService) { }
  getAll(): Observable<[Feedback]> {
    return this.apiService.get('/feedback').pipe(map(data => data));
  }
}
