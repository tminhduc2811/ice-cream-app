import { Recipe } from 'src/app/models/recipe.model';
export interface CartModel {
  items: CartItem[];
}

export interface CartItem {
  recipe: Recipe;
  quantity: number;
}
