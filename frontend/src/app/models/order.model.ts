export interface Order {
  id: number;
  customerId: number;
  customerName: string;
  customerPhone: string;
  customerEmail: string;
  paymentType: string;
  cardNumber: string;
  paymentId: number;
  paymentOption: string;
  createdDate: Date;
  deliveryDetail: string;
  total: number;
  notes: string;
  status: string;
}
