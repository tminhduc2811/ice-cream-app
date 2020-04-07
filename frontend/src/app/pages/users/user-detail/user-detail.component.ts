import { ConfirmModalComponent } from './../../../modals/confirm-modal/confirm-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from './../../../services/auth.service';
import { debounceTime } from 'rxjs/operators';
import { AngularFireStorage } from '@angular/fire/storage';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable, Subject } from 'rxjs';
import { UserService } from './../../../services/user.service';
import { User } from './../../../models/user.model';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  username: string;
  user: User;
  isLoading = false;
  imgLoading = false;
  isSubmitting = false;
  isLoaded = false;

  profileUrl: Observable<string | null>;
  isAdmin = false;
  userRoles = [];
  // Alert messages
  successMessage = '';
  success = new Subject<string>();
  warningMessage = '';
  warning = new Subject<string>();

  formGroup = new FormGroup({
    fullname: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    username: new FormControl(),
    adminCb: new FormControl(),
    userCb: new FormControl(),
    statusCb: new FormControl()
  });

  constructor(private route: ActivatedRoute,
              private userService: UserService,
              private fbStorage: AngularFireStorage,
              private location: Location,
              private auth: AuthService,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.setAlert();
    this.isAdmin = this.auth.isAdmin();
    this.isLoading = true;
    this.username = this.route.snapshot.paramMap.get('username');
    this.userService.getProfileByName(this.username)
      .subscribe(rs => {
        this.user = rs;
        for (const r of this.user.roles) {
          this.userRoles.push(r.role);
        }
        this.setForm();
        this.isLoaded = true;
      });
  }

  setAlert() {

    // Set timeout for alert
    this.success.subscribe(message => this.successMessage = message);
    this.success.pipe(debounceTime(3000)).subscribe(() => this.successMessage = '');
    this.warning.subscribe(message => this.warningMessage = message);
    this.warning.pipe(debounceTime(4000)).subscribe(() => this.warningMessage = '');
  }

  setForm() {
    this.formGroup.setValue({
      fullname: this.user.fullName,
      email: this.user.email,
      username: this.user.userName,
      adminCb: this.userRoles.includes('ADMIN'),
      userCb: this.userRoles.includes('USER'),
      statusCb: this.user.status === 1
    });
    const imagePath = this.user.avatar === '' ? 'images/default.jpg' : this.user.avatar;

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

  submitForm() {
    const modalRef = this.modalService.open(ConfirmModalComponent);
    modalRef.componentInstance.data = {
      header: 'Update information',
      message: 'Do you want to update this profile?',
      subMessage: '',
      danger: false
    };
    modalRef.result.then(() => {

      this.isSubmitting = true;
      const roles = [];
      if (this.formGroup.controls['adminCb'].value) {
        roles.push({ id: 1, role: 'ADMIN' });
      }
      if (this.formGroup.controls['userCb'].value) {
        roles.push({ id: 2, role: 'USER' });
      }
      if (this.formGroup.controls['statusCb'].value) {
        this.user.status = 1;
      } else {
        this.user.status = 0;
      }
      this.user.roles = roles;
      this.userService.updateProfile({ user: this.user, currentPassword: '', newPassword: '' })
        .subscribe(res => {
          this.user = res;
          this.success.next('User information has been save');
          this.isSubmitting = false;
        }, err => {
          this.isSubmitting = false;
          this.warning.next(err.message);
        });
    }).catch(rs => {});
  }

  onCancel() {
    this.location.back();
  }
}
