import { FormGroup, FormControl } from '@angular/forms';
import { debounceTime } from 'rxjs/operators';
import { AuthService } from './../../../services/auth.service';
import { AngularFireStorage } from '@angular/fire/storage';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from './../../../services/customer.service';
import { Observable, Subject } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/customer.model';
import { Location } from '@angular/common';
import { ThrowStmt } from '@angular/compiler';

@Component({
  selector: 'app-customer-detail',
  templateUrl: './customer-detail.component.html',
  styleUrls: ['./customer-detail.component.css']
})
export class CustomerDetailComponent implements OnInit {

  username: string;
  customer: Customer;
  isLoading = false;
  imgLoading = false;
  isSubmitting = false;
  isLoaded = false;

  profileUrl: Observable<string | null>;
  isAuth = false;
  userRoles = [];
  // Alert messages
  successMessage = '';
  success = new Subject<string>();
  warningMessage = '';
  warning = new Subject<string>();

  formGroup = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    gender: new FormControl(''),
    address: new FormControl(''),
    phoneNumber: new FormControl(''),
    email: new FormControl(''),
    birthday: new FormControl(''),
  });

  formAccount = new FormGroup({
    expiredDate: new FormControl(''),
    userName: new FormControl(),
    status: new FormControl(),
  });

  constructor(private route: ActivatedRoute,
              private customerService: CustomerService,
              private fbStorage: AngularFireStorage,
              private location: Location,
              private auth: AuthService) { }

  ngOnInit(): void {
    this.setAlert();
    this.isAuth = this.auth.isAdmin() || this.auth.isUser();
    this.isLoading = true;
    this.username = this.route.snapshot.paramMap.get('username');
    this.customerService.getProfileByName(this.username)
      .subscribe(rs => {
        this.customer = rs;
        this.setForm();
        this.isLoaded = true;
      });
  }

  setForm() {
    this.formGroup.setValue({
      firstName: this.customer.firstName,
      lastName: this.customer.lastName,
      gender: this.customer.gender === 1 ? 'male' : 'female',
      address: this.customer.address,
      phoneNumber: this.customer.phoneNumber,
      email: this.customer.email,
      birthday: this.customer.birthday,
    });

    this.formAccount.setValue({
      expiredDate: this.customer.expiredDate,
      userName: this.customer.userName,
      status: this.customer.status === 1
    });
    const imagePath = this.customer.avatar === '' ? 'images/default.jpg' : this.customer.avatar;

    this.imgLoading = true;

    this.fbStorage.ref(imagePath).getDownloadURL().subscribe(rs => {
      this.profileUrl = rs;
      this.imgLoading = false;
    }, () => {
      // If cannot get the avatar, get default avatar
      this.fbStorage.ref('/images/default.jpg').getDownloadURL().subscribe(temp => {
        this.profileUrl = temp;
        this.imgLoading = false;
      }, () => {
        this.imgLoading = false;
      });
    });
  }

  setAlert() {
    // Set timeout for alert
    this.success.subscribe(message => this.successMessage = message);
    this.success.pipe(debounceTime(3000)).subscribe(() => this.successMessage = '');
    this.warning.subscribe(message => this.warningMessage = message);
    this.warning.pipe(debounceTime(4000)).subscribe(() => this.warningMessage = '');
  }

  onCancel() {
    this.location.back();
  }
  submitForm() {
    this.isSubmitting = true;
    if (this.formAccount.controls['status'].value) {
      this.customer.status = 1;
    } else {
      this.customer.status = 0;
    }
    this.customerService.updateProfile({ customer: this.customer, currentPassword: '', newPassword: '' })
      .subscribe(res => {
        this.customer = this.customer;
        this.success.next('Customer information has been saved successfully');
        this.isSubmitting = false;
      }, err => {
        this.isSubmitting = false;
        this.warning.next(err.message);
      });
  }
}
