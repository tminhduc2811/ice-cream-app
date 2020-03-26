import { RecipeService } from './../../../services/recipe.service';
import { Recipe } from './../recipe.model';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  recipes: Recipe[] = [];
  recipesLoaded = false;

  constructor(private recipeService: RecipeService) {
    this.recipeService.getAll()
      .subscribe(recipes => {
        this.recipes = recipes;
        this.recipesLoaded = true;
      });
  }

  ngOnInit(): void {
  }

}
