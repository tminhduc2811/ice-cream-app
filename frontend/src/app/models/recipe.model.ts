export interface Recipe {
  id: number;
  userId: number;
  icecreamId: number;
  title: string;
  description: string;
  price: number;
  status: number;
  viewCount: number;
  image: string;
  details: string;
  uploadedDate: Date;
}
