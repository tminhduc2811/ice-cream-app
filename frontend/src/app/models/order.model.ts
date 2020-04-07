import { Recipe } from './recipe.model';
import { Payment } from './payment.model';
import { Customer } from './customer.model';

export interface Order {
  id: number;
  customer: Customer;
  payment: Payment;
  orderDetails: Detail[];
  paymentOption: string;
  createdDate: Date;
  notes: string;
  status: string;
  deliveryDetail: string;
}

export interface Detail {
  id: number;
  recipe: Recipe;
  quantity: number;
  total: number;
}
