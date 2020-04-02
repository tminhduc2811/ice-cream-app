import { ConfirmModalComponent } from './../../../modals/confirm-modal/confirm-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
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
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: User = {} as User;
  uploadPercent: Observable<number>;
  profileUrl: Observable<string | null>;
  imgLoading = false;
  isSubmitting = false;
  roles = [];
  // Alert messages
  successMessage = '';
  success = new Subject<string>();
  warningMessage = '';
  warning = new Subject<string>();

  // image = new FormControl();
  formGroup = new FormGroup({
    fullname: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    username: new FormControl(),
    currentPassword: new FormControl('', Validators.minLength(4)),
    newPassword: new FormControl('', Validators.minLength(4))
  });

  constructor(private userService: UserService,
              private auth: AuthService,
              private fb: FormBuilder,
              private fbStorage: AngularFireStorage,
              private router: Router,
              private modalService: NgbModal) {
    this.roles = this.auth.getRoles();
    console.log('check role ', this.roles);
    this.fb.group(this.formGroup);
  }

  ngOnInit(): void {
    this.setAlert();
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

    // const imagePath = this.user.avatar === '' ? 'images/default.jpg' : this.user.avatar;

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
    console.log('type ', file.name.substr(file.name.length - 4, file.name.length - 1));
    task.snapshotChanges().pipe(
      finalize(() => {
        fileRef.getDownloadURL().subscribe(
          data => {
            this.profileUrl = data;
            console.log('Saving to database', this.profileUrl);
            this.user.avatar = data;
            this.userService.updateProfile({ user: this.user, currentPassword: '' })
              .subscribe(rs => {
                console.log('Updated');
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
      this.userService.updateProfile({
        user: this.user,
        currentPassword: this.formGroup.get('currentPassword').value,
        newPassword: this.formGroup.get('newPassword').value
      })
        .subscribe(res => {
          this.user = res;
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
    this.user.fullName = this.formGroup.get('fullname').value;
    this.user.email = this.formGroup.get('email').value;
    this.user.password = this.formGroup.get('newPassword').value;
  }
}

