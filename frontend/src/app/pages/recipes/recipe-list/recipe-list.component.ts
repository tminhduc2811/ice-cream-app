import { Recipe } from './../../../models/recipe.model';
import { RecipeService } from './../../../services/recipe.service';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  recipes: Recipe[] = [];
  recipesLoaded = false;
  isLoading = false;
  // Params

  constructor(private recipeService: RecipeService) {
    this.isLoading = true;
    this.recipeService.getAll()
      .subscribe(rs => {
        this.recipes = rs.content;
        this.recipesLoaded = true;
        this.isLoading = false;
      });
  }

  ngOnInit(): void {
    this.isLoading = true;
    this.recipeService.typeSelected
      .subscribe(idType => {
        console.log('Type id:', idType);
        if (idType === 0) {
          this.recipeService.getAll()
            .subscribe(rs => {
              this.recipes = rs.content;
              this.isLoading = false;
            }, err => {
              // TODO: Handle error later
              this.isLoading = false;
            });
        } else {
          this.recipeService.getAllByType(idType)
            .subscribe(recipes => {
              this.recipes = recipes;
              this.isLoading = false;
            }, err => {
              // TODO: Handle error later
              this.isLoading = false;
            });
        }
      });
  }

}
