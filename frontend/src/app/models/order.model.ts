import { Payment } from './payment.model';
import { Customer } from './customer.model';

export interface Order {
  id: number;
  customer: Customer;
  payment: Payment;
  paymentOption: string;
  createdDate: Date;
  notes: string;
  status: string;
  deliveryDetail: string;
}
