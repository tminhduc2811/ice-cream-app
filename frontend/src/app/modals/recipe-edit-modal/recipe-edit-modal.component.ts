import { RecipeService } from './../../services/recipe.service';
import { finalize } from 'rxjs/operators';
import { AngularFireStorage } from '@angular/fire/storage';
import { AuthService } from './../../services/auth.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit, Input } from '@angular/core';
import { Recipe } from 'src/app/models/recipe.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-recipe-edit-modal',
  templateUrl: './recipe-edit-modal.component.html',
  styleUrls: ['./recipe-edit-modal.component.css']
})
export class RecipeEditModalComponent implements OnInit {
  @Input() type: number;
  @Input() recipe: Recipe;
  isLoaded = false;
  imgLoading = false;
  filePath = '';
  file: any;
  url: any;
  userName = '';
  constructor(public modal: NgbActiveModal,
              private auth: AuthService,
              private fbStorage: AngularFireStorage,
              private recipeService: RecipeService) { }

  ngOnInit(): void {
    this.url = this.recipe.image;
    this.isLoaded = true;
    this.userName = this.auth.getUsername();

  }
  confirm(f: NgForm) {
    if (this.type === 1) {
      if (this.filePath !== '') {
        // Starting to upload to firebase storage
        const fileRef = this.fbStorage.ref(this.filePath);
        const task = fileRef.put(this.file);
        task.snapshotChanges().pipe(
          finalize(() => {
            fileRef.getDownloadURL().subscribe(
              data => {
                this.url = data;
                this.recipe.image = data;
                this.recipe.title = f.value.title;
                this.recipe.description = f.value.description;
                this.recipe.price = f.value.price;
                this.recipe.details = f.value.details;
                this.recipe.user.userName = this.userName;
                if (f.value.status) {
                  this.recipe.status = 1;
                } else {
                  this.recipe.status = 0;
                }
                this.recipeService.newRecipe(this.recipe)
                .subscribe(rs => {
                  this.modal.dismiss({status: true});
                });
              }
            );
          })).subscribe();
      }
    }
    console.log(f.value.title);
  }
  uploadImage(event) {
    const randomId = Math.random().toString(36).substring(2);
    // Starting to upload to firebase storage
    this.file = event.target.files[0];
    this.filePath = '/images/' + randomId + this.file.name.substr(this.file.name.length - 4, this.file.name.length - 1);
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.url = e.target.result;
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  }
}
