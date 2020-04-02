import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmModalComponent } from './../../../modals/confirm-modal/confirm-modal.component';
import { CustomerService } from './../../../services/customer.service';
import { Customer } from './../../../models/customer.model';
import { Router } from '@angular/router';
import { AngularFireStorage } from '@angular/fire/storage';
import 'firebase/storage';
import { Observable, Subject } from 'rxjs';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { AuthService } from './../../../services/auth.service';
import { User } from './../../../models/user.model';
import { UserService } from './../../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { finalize, debounceTime } from 'rxjs/operators';

@Component({
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrls: ['./customer-profile.component.css']
})
export class CustomerProfileComponent implements OnInit {

  customer: Customer = {} as Customer;
  uploadPercent: Observable<number>;
  profileUrl: Observable<string | null>;
  imageName = '';
  imgLoading = false;
  isSubmitting = false;
  // Alert messages
  successMessage = '';
  success = new Subject<string>();
  warningMessage = '';
  warning = new Subject<string>();

  // image = new FormControl();
  formGroup = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    gender: new FormControl('', Validators.required),
    address: new FormControl('', Validators.required),
    phoneNumber: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    birthday: new FormControl('', Validators.required),
    expiredDate: new FormControl('', Validators.required),
    userName: new FormControl(),
    currentPassword: new FormControl('', Validators.minLength(4)),
    newPassword: new FormControl('', Validators.minLength(4))
  });

  constructor(private customerService: CustomerService,
              private auth: AuthService,
              private fb: FormBuilder,
              private fbStorage: AngularFireStorage,
              private router: Router,
              private modalService: NgbModal) {
    this.fb.group(this.formGroup);
  }

  ngOnInit(): void {
    this.setAlert();
    let userName: string;
    this.auth.authInfo.subscribe(data => { userName = data.username; });
    this.customerService.getProfileByName(userName).subscribe(data => {
      this.customer = data;
      this.setForm();
      console.log('user', this.customer);
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
      expiredDate: this.customer.expiredDate,
      userName: this.customer.userName,
      currentPassword: '',
      newPassword: '',
    });

    // const imagePath = this.customer.avatar === '' ? 'images/default.jpg' : this.customer.avatar;

    // this.imgLoading = true;

    // this.fbStorage.ref(imagePath).getDownloadURL().subscribe(rs => {
    //   this.profileUrl = rs;
    //   this.imgLoading = false;
    // }, () => {
    //   // If cannot get the avatar, get default avatar
    //   this.fbStorage.ref('/images/default.jpg').getDownloadURL().subscribe(temp => {
    //     this.profileUrl = temp;
    //     this.imgLoading = false;
    //   }, () => {
    //     this.imgLoading = false;
    //   });
    // });
  }

  setAlert() {

    // Set timeout for alert
    this.success.subscribe(message => this.successMessage = message);
    this.success.pipe(debounceTime(3000)).subscribe(() => this.successMessage = '');
    this.warning.subscribe(message => this.warningMessage = message);
    this.warning.pipe(debounceTime(4000)).subscribe(() => this.warningMessage = '');
  }

  uploadAvatar(event: any) {
    const randomId = Math.random().toString(36).substring(2);
    // Starting to upload to firebase storage
    const file = event.target.files[0];
    const filePath = '/images/' + randomId + file.name.substr(file.name.length - 4, file.name.length - 1);
    const fileRef = this.fbStorage.ref(filePath);
    this.imgLoading = true;
    const task = fileRef.put(file);
    // Save generated file name to store in backend
    // this.imageName = filePath;
    this.uploadPercent = task.percentageChanges();
    task.snapshotChanges().pipe(
      finalize(() => {
        fileRef.getDownloadURL().subscribe(
          data => {
            this.profileUrl = data;
            this.customer.avatar = data;
            this.customerService.updateProfile({ customer: this.customer, currentPassword: '' })
              .subscribe(rs => {
                this.imgLoading = false;
                this.success.next('Your avatar has been saved');
              },
                err => {
                  this.imgLoading = false;
                  console.log('Error: ', err);
                });
          }
        );
      }))
      .subscribe();
  }
  submitForm() {
    const modalRef = this.modalService.open(ConfirmModalComponent);
    modalRef.componentInstance.data = {
      header: 'Update information',
      message: 'Do you want to update your profile?',
      subMessage: '',
      danger: false
    };
    modalRef.result.then(() => {

      this.isSubmitting = true;
      this.getForm();
      this.customerService.updateProfile({ customer: this.customer, currentPassword: this.formGroup.get('currentPassword').value })
        .subscribe(res => {
          this.customer = res;
          this.success.next('Your information has been saved successfully');
          this.isSubmitting = false;
        }, err => {
          this.isSubmitting = false;
          this.warning.next(err.message);
        });
    }).catch(rs => {});
  }

  onCancel() {
    this.router.navigate(['home']);
  }

  getForm() {
    this.customer.firstName = this.formGroup.get('firstName').value;
    this.customer.lastName = this.formGroup.get('lastName').value;
    this.customer.birthday = this.formGroup.get('birthday').value;
    this.customer.gender = this.formGroup.get('gender').value === 'male' ? 1 : 0;
    this.customer.address = this.formGroup.get('address').value;
    this.customer.phoneNumber = this.formGroup.get('phoneNumber').value;
    this.customer.email = this.formGroup.get('email').value;

  }
}

