import { CartItem } from './../../../models/cart.model';
import { Recipe } from 'src/app/models/recipe.model';
import { Component, OnInit, Input } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-my-cart-item',
  templateUrl: './my-cart-item.component.html',
  styleUrls: ['./my-cart-item.component.css']
})
export class MyCartItemComponent implements OnInit {

  @Input() cartItem: CartItem;

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
  }

  setQuantity() {
    this.cartService.modifyCart(this.cartItem);
  }
  removeItem() {
    console.log('check');
    this.cartService.removeItemCart(this.cartItem);
  }
}
