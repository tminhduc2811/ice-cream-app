import { Pageable, Sort } from './../../models/view.model';
import { Customer } from './../../models/customer.model';

export interface CustomerView {
  content: Customer[];
  pageable: Pageable;
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  number: number;
  sort: Sort;
}
