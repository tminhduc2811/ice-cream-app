import { map } from 'rxjs/operators';
import { AuthService } from './../services/auth.service';
import { Errors } from './../shared/list-errors.component';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  authType: string;
  title: string;
  isSubmitting = false;
  authForm: FormGroup;
  error = '';

  constructor(private route: ActivatedRoute,
              private router: Router,
              private formBuilder: FormBuilder,
              private auth: AuthService) {
    this.authForm = this.formBuilder.group({
      // tslint:disable-next-line:object-literal-key-quotes
      'username': ['', Validators.required],
      // tslint:disable-next-line:object-literal-key-quotes
      'password': ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.url.subscribe(data => {
      this.authType = data[data.length - 1].path;
      this.title = (this.authType === 'login') ? 'Login' : 'Register';
      if (this.authType === 'register') {
        this.authForm.addControl('email', new FormControl('email', Validators.required));
      }
    });
  }

  submitForm() {
    console.log('Starting to authenticate');
    this.isSubmitting = true;
    this.error = '';
    const credentials = this.authForm.value;
    this.auth.login(credentials)
      .subscribe(
        response => {
          this.isSubmitting = false;
        },
        err => {
          this.isSubmitting = false;
          this.error = err.message;
        }
      );
  }
}
