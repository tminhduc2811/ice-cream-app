import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { AuthService } from './../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  // isLoggedIn$: Observable<boolean>;
  isLoggedIn = false;
  roles: string[] = [];

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit() {
    this.auth.authInfo.subscribe(val => {
      console.log('this val', val);
      this.isLoggedIn = val.isLoggedIn;
      this.roles = val.roles;
    });
  }

  btnLogout() {
    this.auth.logout();
    this.router.navigate(['/home']);
  }
}
