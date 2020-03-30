import { Recipe } from './../pages/recipes/recipe.model';
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

  getAll(): Observable<Recipe[]> {
    return this.apiService.get('/recipes').pipe(map(data => data));
  }

  getAllByType(id): Observable<Recipe[]> {
    return this.apiService.get('/recipes/type/' + id).pipe(map(data => data));
  }

}
