import { IceCream } from './../../models/ice-cream.model';
import { ConfirmModalComponent } from './../confirm-modal/confirm-modal.component';
import { RecipeService } from './../../services/recipe.service';
import { finalize, debounceTime, map } from 'rxjs/operators';
import { AngularFireStorage } from '@angular/fire/storage';
import { AuthService } from './../../services/auth.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit, Input } from '@angular/core';
import { Recipe } from 'src/app/models/recipe.model';
import { NgForm } from '@angular/forms';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-recipe-edit-modal',
  templateUrl: './recipe-edit-modal.component.html',
  styleUrls: ['./recipe-edit-modal.component.css']
})
export class RecipeEditModalComponent implements OnInit {
  @Input() type: number;
  @Input() recipe: Recipe;
  @Input() icecreams: IceCream[] = [];
  isLoaded = false;
  imgLoading = false;
  filePath = '';
  file: any;
  url: any;
  userName = '';
  warningMessage = '';
  warning = new Subject<string>();

  constructor(public modal: NgbActiveModal,
              private auth: AuthService,
              private fbStorage: AngularFireStorage,
              private recipeService: RecipeService,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.url = this.recipe.image;
    this.isLoaded = true;
    this.userName = this.auth.getUsername();

  }
  setAlert() {

    // Set timeout for alert
    this.warning.subscribe(message => this.warningMessage = message);
    this.warning.pipe(debounceTime(4000)).subscribe(() => this.warningMessage = '');
  }
  confirm(f: NgForm) {
    console.log(f.value.type);
    const confirmRef = this.modalService.open(ConfirmModalComponent);
    confirmRef.componentInstance.data = {
      header: 'Update this recipe?',
      message: '',
      subMessage: 'Recipe with id ' + this.recipe.id + ' will be updated',
      danger: false
    };
    confirmRef.result.then(() => {
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
                      this.modal.close({ status: true });
                    }, err => {
                      this.warning.next(err.message);
                    });
                }
              );
            })).subscribe();
        }
      } else {
        this.recipe.title = f.value.title;
        this.recipe.description = f.value.description;
        this.recipe.price = f.value.price;
        this.recipe.details = f.value.details;
        if (f.value.status) {
          this.recipe.status = 1;
        } else {
          this.recipe.status = 0;
        }
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
                  this.recipeService.updateRecipe(this.recipe.id, this.recipe)
                    .subscribe(rs => {
                      this.modal.close({status: true});
                    }, err => {
                      this.warning.next(err.message);
                    });
                }
              );
            })).subscribe();
        } else {
          this.recipeService.updateRecipe(this.recipe.id, this.recipe)
            .subscribe(rs => {
              this.modal.close({status: true});
            });
        }
      }
    }).catch(rs => { });
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
  iceCreamSelected(event) {
    const index = this.icecreams.map(e => e.name).indexOf(event);
    this.recipe.icecream = this.icecreams[index];
  }
}
