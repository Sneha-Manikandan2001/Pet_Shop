import { Addresses } from './addresses';
import { Transactions } from './Transaction';
 
export interface Customers {
    customerId: number;
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    address: Addresses;
    transactions: Transactions[];
   
}
 