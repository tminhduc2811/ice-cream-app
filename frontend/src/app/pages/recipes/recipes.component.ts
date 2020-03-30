import { IceCreamService } from './../../services/ice-cream.service';
import { RecipeService } from './../../services/recipe.service';
import { Component, OnInit } from '@angular/core';
import { IceCream } from 'src/app/models/ice-cream.model';
import { Recipe } from 'src/app/models/recipe.model';

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

  constructor(private recipeService: RecipeService, private iceCreamService: IceCreamService) { }

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
}
