import { AngularFireStorage } from '@angular/fire/storage';
import 'firebase/storage';
import { Observable } from 'rxjs';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { AuthService } from './../../../services/auth.service';
import { User } from './../../../models/user.model';
import { UserService } from './../../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { finalize } from 'rxjs/operators';
@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: User = {} as User;
  uploadPercent: Observable<number>;
  profileUrl: Observable<string | null>;
  imageName = '';
  imgLoading = false;

  // image = new FormControl();
  formGroup = new FormGroup({
    fullname: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    username: new FormControl(),
    currentPassword: new FormControl('', Validators.required),
    newPassword: new FormControl('', Validators.required)
  });

  constructor(private userService: UserService, private auth: AuthService, private fb: FormBuilder, private afStorage: AngularFireStorage) {
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

      // If user doesn't have an avatar, set default
      const imagePath = this.user.avatar === '' ? 'images/default.jpg' : this.user.avatar;

      this.imgLoading = true;

      this.afStorage.ref(imagePath).getDownloadURL().subscribe(rs => {
        this.profileUrl = rs;
        this.imgLoading = false;
      }, () => {
        this.afStorage.ref('/images/default.jpg').getDownloadURL().subscribe(temp => {
          this.profileUrl = temp;
          this.imgLoading = false;
        }, () => {
          this.imgLoading = false;
        });
      });
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

  uploadAvatar(event: any) {
    const randomId = Math.random().toString(36).substring(2);
    // Starting to upload to firebase storage
    const file = event.target.files[0];
    const filePath = '/images/' + randomId;
    const fileRef = this.afStorage.ref(filePath);
    const task = fileRef.put(file);
    // Save generated file name to store in backend
    this.imageName = filePath;
    console.log(event.target.files[0].name);
    this.uploadPercent = task.percentageChanges();
    task.snapshotChanges().pipe(
      finalize(() => this.profileUrl = fileRef.getDownloadURL())
    ).subscribe(

    );
  }
  submitForm() {
  }

}

