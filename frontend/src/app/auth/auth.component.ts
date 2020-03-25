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
  errors: Errors = { errors: {} };

  constructor(private route: ActivatedRoute, private router: Router, private formBuilder: FormBuilder) {
    this.authForm = this.formBuilder.group({
      // tslint:disable-next-line:object-literal-key-quotes
      'username': ['', Validators.minLength(4)],
      // tslint:disable-next-line:object-literal-key-quotes
      'password': ['', Validators.minLength(6)]
    });
  }

  ngOnInit(): void {
    this.route.url.subscribe(data => {
      this.authType = data[data.length - 1].path;
      this.title = (this.authType === 'login') ? 'Login' : 'Register';
      if (this.authType === 'register') {
        console.log('asdf');
        this.authForm.addControl('email', new FormControl('email', Validators.required));
      }
    });
  }

}
