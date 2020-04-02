import { AngularFireStorage } from '@angular/fire/storage';
import { RecipeModalComponent } from './../../../../modals/recipe-modal/recipe-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from './../../../../services/auth.service';
import { RecipeService } from './../../../../services/recipe.service';
import { Component, OnInit, Input } from '@angular/core';
import { Recipe } from 'src/app/models/recipe.model';

@Component({
  selector: 'app-recipe-item',
  templateUrl: './recipe-item.component.html',
  styleUrls: ['./recipe-item.component.css']
})
export class RecipeItemComponent implements OnInit {

  @Input() recipe: Recipe;
  roles: string[] = [];
  imageUrl = '';
  imgLoading = false;
  constructor(private recipeService: RecipeService,
              private auth: AuthService,
              private modalService: NgbModal,
              private fbService: AngularFireStorage) {
  }

  ngOnInit(): void {
    this.auth.authInfo.subscribe(data => {
      this.roles = data.roles;
    });
    this.fbService.ref(this.recipe.image).getDownloadURL()
    .subscribe(rs => {
      console.log(rs);
      this.imageUrl = rs;
      this.imgLoading = false;
    }, () => {
      this.imgLoading = false;
    });
  }

  showDetail() {
    console.log(this.recipe);
    const modalRef = this.modalService.open(RecipeModalComponent, {centered: true, size: 'lg'});
    modalRef.componentInstance.recipe = this.recipe;
  }
}
