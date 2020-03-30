import { HttpErrorResponse } from '@angular/common/http';
import { AuthInfo } from './../models/auth-info.model';
import { ApiService } from './api.service';
import { Observable, BehaviorSubject, ReplaySubject, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Injectable } from '@angular/core';
import { distinctUntilChanged, map, catchError } from 'rxjs/operators';
import { error } from 'protractor';

@Injectable()
export class AuthService {
  private loggedIn = new BehaviorSubject<boolean>(this.isAuthenticated());
  private authInfoSubject = new BehaviorSubject<AuthInfo>({
    isLoggedIn: this.isAuthenticated(),
    roles: this.getRoles(),
    username: this.getUsername(),
    avatar: this.getAvatar()
  });
  public authInfo = this.authInfoSubject.pipe(distinctUntilChanged());
  public isLoggedIn = this.loggedIn.asObservable().pipe(distinctUntilChanged());
  helper = new JwtHelperService();
  constructor(private apiService: ApiService, private router: Router) {

  }

  login(credentials): Observable<any> {
    return this.apiService.post('/auth', credentials)
      .pipe(map(
        data => {
          this.finishAuthentication(data);
          return data;
        }
      ));
  }

  finishAuthentication(data): void {
    const token = this.helper.decodeToken(data.token);
    const expiresAt = JSON.stringify((token.exp * 1000));
    // save data to local storage
    localStorage.setItem('user-name', token.sub);
    localStorage.setItem('access-token', data.token);
    localStorage.setItem('expires-at', expiresAt);
    localStorage.setItem('user-role', token.role);
    this.loggedIn.next(true);
    // set current data to observable
    this.authInfoSubject.next({
      isLoggedIn: true,
      roles: this.getRoles(),
      username: this.getUsername(),
      avatar: this.getAvatar()
    });

    this.router.navigate(['home']);
  }

  logout(): Observable<any> {
    localStorage.removeItem('user-name');
    localStorage.removeItem('access-token');
    localStorage.removeItem('expires-at');
    localStorage.removeItem('user-role');
    this.authInfoSubject.next({
      isLoggedIn: false,
      roles: this.getRoles(),
      username: this.getUsername(),
      avatar: this.getAvatar()
    });

    return this.isLoggedIn;
  }

  isAuthenticated(): boolean {
    const expiresAt = JSON.parse(localStorage.getItem('expires-at'));
    return Date.now() < expiresAt;
  }

  getToken(): string {
    return localStorage.getItem('access-token');
  }

  getRoles(): string[] {
    const roles = localStorage.getItem('user-role');
    let rs = [];
    if (roles) {
      rs = roles.split(',');
    }
    return rs;
  }
  getUsername(): string {
    return localStorage.getItem('user-name');
  }

  getAvatar(): string {
    return localStorage.getItem('avatar');
  }

  isAdmin() {
    const roles = localStorage.getItem('user-role');
    if (roles) {
      return roles.includes('ROLE_ADMIN');
    }
    return false;
  }

  isCustomer() {
    const roles = localStorage.getItem('user-role');
    if (roles) {
      return roles.includes('ROLE_CUSTOMER');
    }
    return false;
  }

  isUser() {
    const roles = localStorage.getItem('user-role');
    if (roles) {
      return roles.includes('ROLE_USER');
    }
    return false;
  }
}
