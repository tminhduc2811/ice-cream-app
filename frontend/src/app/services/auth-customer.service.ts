import { AuthService } from './auth.service';
import { CanActivate, Router } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class CustomerGuard implements CanActivate {
  constructor(private auth: AuthService, private router: Router) {

  }
  canActivate() {
    if (this.auth.isCustomer() && this.auth.isAuthenticated()) {
      return true;
    } else {
      this.router.navigate(['login']);
    }
  }
}
