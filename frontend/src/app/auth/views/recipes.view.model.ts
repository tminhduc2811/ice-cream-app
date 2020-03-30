import { Pageable, Sort } from './../../models/view.model';
import { Recipe } from './../../models/recipe.model';

export interface RecipeView {
  content: Recipe[];
  pageable: Pageable;
  sort: Sort;
}
