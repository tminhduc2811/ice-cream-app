import { Pageable, Sort } from './../../models/view.model';
import { Recipe } from './../../models/recipe.model';

export interface RecipeView {
  content: Recipe[];
  pageable: Pageable;
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  number: number;
  sort: Sort;
}
