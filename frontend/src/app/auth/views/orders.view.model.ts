import { Pageable, Sort } from './../../models/view.model';
import { Order } from './../../models/order.model';
export interface OrderView {
  content: Order[];
  pageable: Pageable;
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  number: number;
  sort: Sort;
}
