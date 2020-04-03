import { HttpParams } from '@angular/common/http';
import { Recipe } from './../models/recipe.model';
import { RecipeView } from './../auth/views/recipes.view.model';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Injectable, EventEmitter } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  typeSelected = new EventEmitter<number>();

  constructor(private apiService: ApiService) { }

  getAll(query): Observable<RecipeView> {
    if (query) {
      const params = new HttpParams().set('page', query.page).set('size', query.size);
      return this.apiService.get('/recipes', params).pipe(map(data => data));
    }
    return this.apiService.get('/recipes').pipe(map(data => data));
  }

  getAllByType(id): Observable<Recipe[]> {
    return this.apiService.get('/recipes/type/' + id).pipe(map(data => data));
  }

  newRecipe(recipe: Recipe): Observable<Recipe> {
    return this.apiService.post('/recipes', recipe).pipe(map(data => data));
  }

  updateRecipe(id:number, recipe: Recipe): Observable<Recipe>{
    return this.apiService.put('/recipes/' + id, recipe).pipe(map(data => data));
  }
}
