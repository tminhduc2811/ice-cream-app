export interface Payment {
  id: number;
  cardType: string;
  cardNumber: string;
  cvv: string;
  name: string;
  expiredDate: Date;
  dateOfBirth: Date;
}
