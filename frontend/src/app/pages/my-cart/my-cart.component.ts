import { Router } from '@angular/router';
import { ConfirmModalComponent } from './../../modals/confirm-modal/confirm-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CheckoutModel, OrderDetail } from './../../models/checkout.model';
import { OrderService } from 'src/app/services/order.service';
import { Payment } from './../../models/payment.model';

import { AuthService } from './../../services/auth.service';
import { Customer } from 'src/app/models/customer.model';
import { CustomerService } from './../../services/customer.service';
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
  isLoaded = false;
  customer: Customer;
  noItem = false;

  constructor(private cartService: CartService,
              private customerService: CustomerService,
              private auth: AuthService,
              private orderService: OrderService,
              private modalService: NgbModal,
              private router: Router) { }

  ngOnInit(): void {
    this.cart = this.cartService.getCartFromLocalStorage();
    if (this.cart) {
      this.cartService.setTotal();
    }
    this.cartService.totalBill.subscribe(rs => this.totalBill = rs);
    this.cartService.cartChanged.subscribe(rs => {
      this.cart = rs;
      if (this.cart.items.length === 0) {
        this.noItem = true;
      }
    });
    this.customerService.getProfileByName(this.auth.getUsername())
      .subscribe(rs => {
        this.customer = rs;
        this.isLoaded = true;
      });
  }

  confirm(form) {
    const modalRef = this.modalService.open(ConfirmModalComponent, {centered: true});
    modalRef.componentInstance.data = {
      header: 'Check out',
      message: 'Do you want to save your order?',
      subMessage: 'After you submit the other, you cannot change it. However you can contact the user to change it',
      danger: true
    };
    modalRef.result.then(() => {
      let checkout: CheckoutModel;
      let payment: Payment;
      const orderDetails: OrderDetail[] = [];
      if (this.paymentType === 'Card') {
        payment = {
          id: null,
          cardType: form.value.cardType,
          cardNumber: form.value.cardNumber,
          cvv: form.value.cvv,
          name: form.value.fullNameCard,
          expiredDate: form.value.expiredDate,
          dateOfBirth: form.value.dateOfBirth
        };
      }
      if (this.cart) {
        for (const item of this.cart.items) {
          orderDetails.push({
            recipeId: item.recipe.id,
            quantity: item.quantity,
            total: Number((item.quantity * item.recipe.price).toFixed(2))
          });
        }
      }
      checkout = {
        order: {
          id: null,
          customer: this.customer,
          payment,
          paymentOption: form.value.paymentType,
          createdDate: null,
          deliveryDetail: form.value.deliveryDetail,
          notes: form.value.notes,
          status: 'New'
        },
        orderDetails
      };
      this.orderService.createOrder(checkout)
        .subscribe(rs => {
          console.log('Order created. ', rs);
          this.cartService.clearCart();
          this.router.navigate(['my-cart/checkout-success']);
        });
    }).catch(err => {});
  }
  goBack() {
    this.router.navigate(['/recipes']);
  }
}
