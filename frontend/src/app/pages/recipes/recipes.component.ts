import { User } from './../../models/user.model';
import { RecipeEditModalComponent } from './../../modals/recipe-edit-modal/recipe-edit-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Recipe } from './../../models/recipe.model';
import { IceCreamService } from './../../services/ice-cream.service';
import { RecipeService } from './../../services/recipe.service';
import { Component, OnInit } from '@angular/core';
import { IceCream } from 'src/app/models/ice-cream.model';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css']
})
export class RecipesComponent implements OnInit {

  selectedRecipe: Recipe;
  iceCreams: IceCream[] = [];
  isLoaded = false;
  selectedId: number;

  constructor(private recipeService: RecipeService, private iceCreamService: IceCreamService, private modalService: NgbModal) { }

  ngOnInit(): void {
    // this.recipeService.recipeSelected
    //   .subscribe((recipe: Recipe) => {
    //     this.selectedRecipe = recipe;
    //   });

    this.iceCreamService.getAll()
      .subscribe(rs => {
        this.iceCreams = rs;
        this.isLoaded = true;
      });
  }
  iceCreamSelected(index) {
    console.log(index);
    if (index < this.iceCreams.length) {
      this.recipeService.typeSelected.emit(this.iceCreams[index].id);
    } else {
      this.recipeService.typeSelected.emit(0);
    }
  }

  newRecipe() {
    const recipe: Recipe = {} as Recipe;
    const icecream: IceCream = {} as IceCream;
    const user: User = {} as User;
    recipe.user = user;
    recipe.icecream = icecream;

    const modalRef = this.modalService.open(RecipeEditModalComponent);
    modalRef.componentInstance.type = 1;
    modalRef.componentInstance.recipe = recipe;
  }
}
