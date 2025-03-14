import { Addresses } from "./addresses";

export interface Supplier {
  supplierId: number;
  name: string;
  contactPerson: string;
  phoneNumber: string;
  email: string;
  address: Addresses;
}