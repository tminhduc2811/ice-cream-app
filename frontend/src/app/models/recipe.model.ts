import { User } from './user.model';
import { IceCream } from './ice-cream.model';
export interface Recipe {
  id: number;
  user: User;
  icecream: IceCream;
  title: string;
  description: string;
  price: number;
  status: number;
  viewCount: number;
  image: string;
  details: string;
  uploadedDate: Date;
}
