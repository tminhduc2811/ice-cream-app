import { HttpParams } from '@angular/common/http';
import { UserView } from './../auth/views/users.view.model';
import { User } from './../models/user.model';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private apiService: ApiService) { }

  createUser(req): Observable<any> {
    return this.apiService.post('/users/create', req).pipe(map(data => data));
  }

  getAll(query): Observable<UserView> {
    if (query) {
      const params = new HttpParams().set('page', query.page).set('size', query.size);
      return this.apiService.get('/users', params).pipe(map(data => data));
    }
    return this.apiService.get('/users').pipe(map(data => data));
  }

  getProfileByName(username: string): Observable<User> {
    return this.apiService.get('/profile/user/' + username).pipe(map(data => data));
  }

  updateProfile(obj: {}): Observable<User> {
    return this.apiService.put('/profile/user/', obj).pipe(map(data => data));
  }

  deleteProfile(id: number) {
    return this.apiService.delete('/users/' + id);
  }
}
