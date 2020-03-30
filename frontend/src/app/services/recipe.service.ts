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

  getAll(): Observable<RecipeView> {
    return this.apiService.get('/recipes').pipe(map(data => data));
  }

  getAllByType(id): Observable<Recipe[]> {
    return this.apiService.get('/recipes/type/' + id).pipe(map(data => data));
  }

}
