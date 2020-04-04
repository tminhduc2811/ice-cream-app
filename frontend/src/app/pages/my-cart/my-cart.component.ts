import { CartModel } from './../../models/cart.model';
import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-my-cart',
  templateUrl: './my-cart.component.html',
  styleUrls: ['./my-cart.component.css']
})
export class MyCartComponent implements OnInit {

  paymentType: string;
  cart: CartModel;
  totalBill: number;
  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.cart = this.cartService.getCartFromLocalStorage();
    this.cartService.setTotal();
    this.cartService.totalBill.subscribe(rs => this.totalBill = rs);
    this.cartService.cartChanged.subscribe(rs => this.cart = rs);
  }

}
