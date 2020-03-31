import { Role } from './role.model';
export interface User {
  id: number;
  userName: string;
  password: string;
  fullName: string;
  email: string;
  status: number;
  avatar: string;
  roles: Role[];
}
