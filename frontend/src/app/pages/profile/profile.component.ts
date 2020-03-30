import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  isCustomer = false;
  isUserOrAdmin = false;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.isCustomer = this.authService.isCustomer();
    console.log(this.isUserOrAdmin);
    this.isUserOrAdmin = this.authService.isUser() || this.authService.isAdmin();
  }

}
