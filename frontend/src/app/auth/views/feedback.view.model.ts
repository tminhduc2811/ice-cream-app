import { Pageable, Sort } from './../../models/view.model';
import { Feedback } from './../../models/feedback.model';
export interface FeedbackView {
  content: Feedback[];
  pageable: Pageable;
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  number: number;
  sort: Sort;
}
