import { AuthInfo } from './../models/auth-info.model';
import { ApiService } from './api.service';
import { Observable, BehaviorSubject, ReplaySubject } from 'rxjs';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Injectable } from '@angular/core';
import { distinctUntilChanged, map } from 'rxjs/operators';

@Injectable()
export class AuthService {
  private loggedIn = new BehaviorSubject<boolean>(this.isAuthenticated());
  // private typeAdmin = new BehaviorSubject<boolean>(this.isAdmin());
  // private typeUser = new BehaviorSubject<boolean>(this.isUser());
  // private typeCustomer = new BehaviorSubject<boolean>(this.isCustomer());
  private authInfoSubject = new BehaviorSubject<AuthInfo>({
    isLoggedIn: this.isAuthenticated(),
    roles: this.getRoles2()
  });
  public authInfo = this.authInfoSubject.pipe(distinctUntilChanged());
  public isLoggedIn = this.loggedIn.asObservable().pipe(distinctUntilChanged());
  // public isAdminRole = this.typeAdmin.asObservable().pipe(distinctUntilChanged());
  // public isUserrole = this.typeAdmin.asObservable().pipe(distinctUntilChanged());
  // public isCustomerRole = this.typeAdmin.asObservable().pipe(distinctUntilChanged());

  helper = new JwtHelperService();
  constructor(private apiService: ApiService, private router: Router) {

  }

  login(credentials): Observable<any> {
    return this.apiService.post('/users/login', credentials);
  }

  finishAuthentication(data): void {
    const token = this.helper.decodeToken(data);
    const expiresAt = JSON.stringify((token.exp * 1000) + Date.now());
    // save data to local storage
    localStorage.setItem('access-token', data);
    localStorage.setItem('expires-at', expiresAt);
    localStorage.setItem('user-role', token.role);
    this.loggedIn.next(true);
    // set current data to observable
    this.authInfoSubject.next({ isLoggedIn: true, roles: this.getRoles2() });

    this.router.navigate(['home']);
  }

  logout(): Observable<any> {
    localStorage.removeItem('access-token');
    localStorage.removeItem('expires-at');
    localStorage.removeItem('user-role');
    this.loggedIn.next(false);
    this.authInfoSubject.next({ isLoggedIn: true, roles: this.getRoles2() });

    console.log('logged out');
    return this.isLoggedIn;
  }

  isAuthenticated(): boolean {
    const expiresAt = JSON.parse(localStorage.getItem('expires-at'));
    console.log('asdfasdffffff', Date.now() < expiresAt);
    return Date.now() < expiresAt;
  }

  getToken(): string {
    return localStorage.getItem('access-token');
  }

  getRoles(): string {
    return localStorage.getItem('user-role');
  }
  getRoles2(): string[] {
    const roles = this.getRoles();
    let rs = [];
    if (roles) {
      rs = roles.split(',');
    }
    return rs;
  }

  isAdmin(): boolean {
    const roles = this.getRoles();
    if (roles) {
      if (roles.includes('ADMIN')) {
        return true;
      }
    }
    return false;
  }

  isCustomer(): boolean {
    const roles = this.getRoles();
    if (roles) {
      if (roles.includes('CUSTOMER')) {
        return true;
      }
    }
    return false;
  }

  isUser(): boolean {
    const roles = this.getRoles();
    if (roles) {
      if (roles.includes('USER')) {
        return true;
      }
    }
    return false;
  }

  getUserRole(): string {
    return this.helper.decodeToken(this.getToken()).scope;
  }

}
