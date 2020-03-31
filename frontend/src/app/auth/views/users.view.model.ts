import { Pageable, Sort } from './../../models/view.model';
import { User } from './../../models/user.model';
export interface UserView {
  content: User[];
  pageable: Pageable;
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  number: number;
  sort: Sort;
}
