export interface Customer {
  id: number;
  userName: string;
  password: string;
  firstName: string;
  lastName: string;
  address: string;
  phoneNumber: string;
  email: string;
  gender: number;
  birthday: Date;
  avatar: string;
  expiredDate: Date;
  status: number;
  numOfLoginFailed: number;
}
