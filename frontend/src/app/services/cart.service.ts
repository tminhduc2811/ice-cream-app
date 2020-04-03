import { map } from 'rxjs/operators';
import { Recipe } from 'src/app/models/recipe.model';
import { Injectable } from '@angular/core';
import { CartModel, CartItem } from '../models/cart.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cart: CartModel;
  cartItem: CartItem;

  constructor() { }

  getCartFromLocalStorage() {
    return JSON.parse(localStorage.getItem('cart'));
  }
  saveItem(item: Recipe, q: number) {
    let cart: CartModel;

    cart = this.getCartFromLocalStorage();
    if (cart) {
      const index = cart.items.map(e => e.recipe.id).indexOf(item.id);
      console.log(index);
      if (index !== -1) {
        cart.items[index].quantity += q;
        console.log(cart.items[index].quantity);
        localStorage.setItem('cart', JSON.stringify(cart));
      } else {
        cart.items.push({recipe: item, quantity: q});
        localStorage.setItem('cart', JSON.stringify(cart));
      }
    } else {
      cart = {items: [{recipe: item, quantity: q}]};
      localStorage.setItem('cart', JSON.stringify(cart));
    }
  }
  clearCart() {
    localStorage.removeItem('cart');
  }
}
