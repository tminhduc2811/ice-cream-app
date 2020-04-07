import { map, distinctUntilChanged } from 'rxjs/operators';
import { Recipe } from 'src/app/models/recipe.model';
import { Injectable } from '@angular/core';
import { CartModel, CartItem } from '../models/cart.model';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private total = new BehaviorSubject<number>(0);
  public totalBill = this.total.asObservable().pipe(distinctUntilChanged());

  private cartChange = new BehaviorSubject<CartModel>(this.getCartFromLocalStorage());
  public cartChanged = this.cartChange.asObservable().pipe(distinctUntilChanged());
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
      if (index !== -1) {
        cart.items[index].quantity += q;
        localStorage.setItem('cart', JSON.stringify(cart));
        this.cartChange.next(cart);
      } else {
        cart.items.push({ recipe: item, quantity: q });
        localStorage.setItem('cart', JSON.stringify(cart));
        this.cartChange.next(cart);
      }
    } else {
      cart = { items: [{ recipe: item, quantity: q }] };
      localStorage.setItem('cart', JSON.stringify(cart));
      this.cartChange.next(cart);
    }
    this.setTotal();
  }
  modifyCart(item: CartItem) {
    const cart = this.getCartFromLocalStorage();
    if (cart) {
      const index = cart.items.map(e => e.recipe.id).indexOf(item.recipe.id);
      if (index !== - 1) {
        cart.items[index].quantity = item.quantity;
        localStorage.setItem('cart', JSON.stringify(cart));
        this.setTotal();
        this.cartChange.next(cart);
      }
    }
  }
  clearCart() {
    localStorage.removeItem('cart');
    this.cartChange.next(null);
  }

  removeItemCart(item: CartItem) {
    const cart: CartModel = this.getCartFromLocalStorage();
    if (cart) {
      const index = cart.items.map(e => e.recipe.id).indexOf(item.recipe.id);
      if (index !== -1) {
        cart.items.splice(index, 1);
        localStorage.setItem('cart', JSON.stringify(cart));
        this.setTotal();
        this.cartChange.next(cart);
      }
    }
  }

  setTotal() {
    let total = 0;
    const cart = this.getCartFromLocalStorage();
    for (const item of cart.items) {
      total += item.quantity * item.recipe.price;
    }
    this.total.next(total);
  }
}
