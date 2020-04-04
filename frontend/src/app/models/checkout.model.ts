import { Order } from './order.model';
export interface CheckoutModel {
  order: Order;
  orderDetails: OrderDetail[];
}

export interface OrderDetail {
  recipeId: number;
  quantity: number;
  total: number;
}
