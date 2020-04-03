import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { AuthService } from './../services/auth.service';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  // isLoggedIn$: Observable<boolean>;
  isLoggedIn = false;
  roles: string[] = [];
  isCustomer = false;

  constructor(private auth: AuthService, private router: Router) {
  }

  ngOnInit() {
    this.auth.authInfo.subscribe(val => {
      this.isLoggedIn = val.isLoggedIn;
      this.roles = val.roles;
      this.isCustomer = this.auth.isCustomer();
    });
  }

  btnLogout() {
    this.auth.logout();
    this.router.navigate(['/home']);

  }
  navigateToCart() {
    this.router.navigate(['/my-cart']);
  }
}
