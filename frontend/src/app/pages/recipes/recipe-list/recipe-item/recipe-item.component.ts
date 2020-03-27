import { AuthService } from './../../../../services/auth.service';
import { RecipeService } from './../../../../services/recipe.service';
import { Recipe } from './../../recipe.model';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-recipe-item',
  templateUrl: './recipe-item.component.html',
  styleUrls: ['./recipe-item.component.css']
})
export class RecipeItemComponent implements OnInit {

  @Input() recipe: Recipe;
  roles: string[] = [];

  constructor(private recipeService: RecipeService, private auth: AuthService) {
  }

  ngOnInit(): void {
    this.auth.authInfo.subscribe(data => {
      this.roles = data.roles;
    });
  }

  onSelected() {
    this.recipeService.recipeSelected.emit(this.recipe);
  }
}
