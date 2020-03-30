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

  getAll(): Observable<[User]> {
    return this.apiService.get('/users').pipe(map(data => data));
  }

  getProfileByName(username: string): Observable<User> {
    return this.apiService.get('/profile/user/' + username).pipe(map(data => data));
  }

  updateProfile(obj: {}): Observable<User> {
    return this.apiService.put('/profile/user/', obj).pipe(map(data => data));
  }

}
