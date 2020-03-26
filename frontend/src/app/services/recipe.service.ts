import { Recipe } from './../pages/recipes/recipe.model';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Injectable, EventEmitter } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  recipeSelected = new EventEmitter<Recipe>();

  constructor(private apiService: ApiService) { }

  getAll(): Observable<[Recipe]> {
    return this.apiService.get('/recipes').pipe(map(data => data));
  }
}
