import { AuthService } from './auth.service';
import { CanActivate, Router, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class RoleGuard implements CanActivate {
  constructor(private auth: AuthService, private router: Router) {

  }
  canActivate(route: ActivatedRouteSnapshot) {

    const roles = route.data['roles'] as Array<string>;
    if (roles.includes('admin') && this.auth.isAdmin() && this.auth.isAuthenticated()) {
      return true;
    }
    if (roles.includes('user') && (this.auth.isUser() && this.auth.isAuthenticated())) {
      return true;
    }
    if (roles.includes('customer') && this.auth.isCustomer() && this.auth.isAuthenticated()) {
      return true;
    }
    return this.router.navigate(['login']);

  }
}
