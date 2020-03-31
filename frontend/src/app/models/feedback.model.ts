import { Payment } from './payment.model';
import { Order } from './order.model';
export interface Feedback {
  id: number;
  details: string;
  createdDate: Date;
  order: Order;
}
