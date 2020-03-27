import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
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

  user: User = {} as User;
  image = new FormControl();
  formGroup = new FormGroup({
    fullname: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    username: new FormControl(),
    currentPassword: new FormControl('', Validators.required),
    newPassword: new FormControl('', Validators.required)
  });

  constructor(private userService: UserService, private auth: AuthService, private fb: FormBuilder) {
    this.user.roles = [''];
    this.fb.group(this.formGroup);
  }

  ngOnInit(): void {
    let username: string;
    this.auth.authInfo.subscribe(data => { username = data.username; });
    this.userService.getProfileByName(username).subscribe(data => {
      this.user = data;
      this.setForm();
      console.log('user', this.user);
    });

  }

  setForm() {
    this.formGroup.setValue({
      fullname: this.user.fullName,
      email: this.user.email,
      username: this.user.userName,
      currentPassword: '',
      newPassword: '',
    });
  }
  submitForm() {
    console.log('image link ', this.image.value);
    console.log(this.formGroup.getRawValue());
  }
}
