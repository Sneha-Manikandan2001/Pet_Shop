import { Customers } from './Customer';
import { Pet } from './Pets';
import { TransactionStatus } from './TransactionStatus';
 
export interface Transactions {
  transactionId: number;
  customerId: Customers['customerId'];
  petId: Pet['petId'];
  transactionDate: Date;
  amount: number;
  transactionStatus: TransactionStatus;
}
 