import { AuthService } from './../../../services/auth.service';
import { User } from './../../../models/user.model';
import { UserService } from './../../../services/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: User;

  constructor(private userService: UserService, private auth: AuthService) { }

  ngOnInit(): void {
    let username: string;
    this.auth.authInfo.subscribe(data => { username = data.username; });
    this.userService.getProfileByName(username).subscribe(data => {
      this.user = data;
    });
    console.log(this.user);
  }

}
