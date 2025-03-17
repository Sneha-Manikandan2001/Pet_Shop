import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../../services/transactionservices';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Transactions } from '../../models/Transaction';
 
@Component({
  selector: 'app-transaction',
  imports: [CommonModule, FormsModule],
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {
  transactions: Transactions[] = [];
  newTransaction: Transactions = {} as Transactions;
  showAddTransactionForm: boolean = false;
  editTransactionIndex: number | null = null;
  showEditForm: boolean = false;
  errorMessage: string | null = null;
  filterType: string = 'getAll';
  filterValue: string = '';
 
  constructor(private transactionService: TransactionService) {}
 
  ngOnInit(): void {
    this.getTransactions();
  }
 
  // Fetch transactions based on filter type
  getTransactions(): void {
    switch (this.filterType) {
      case 'getAll':
        this.transactionService.getAllTransactions().subscribe(
          (data) => {
            this.transactions = data;
          },
          (error) => {
            console.error('Error fetching transactions:', error);
            //this.errorMessage = 'Failed to load transactions. Please try again later.';
          }
        );
        break;
      case 'getById':
        this.transactionService.getTransactionById(Number(this.filterValue)).subscribe(
          (data) => {
            this.transactions = [data];
          },
          (error) => {
            console.error('Error fetching transaction by ID:', error);
            //this.errorMessage = 'Failed to load transaction. Please try again later.';
          }
        );
        break;
      case 'getByCustomerId':
        this.transactionService.getTransactionsByCustomerId(Number(this.filterValue)).subscribe(
          (data) => {
            this.transactions = data;
            console.log(this.transactions);
          },
          (error) => {
            console.error('Error fetching transactions by customer ID:', error);
            //this.errorMessage = 'Failed to load transactions. Please try again later.';
          }
        );
        break;
      case 'getSuccessful':
        this.transactionService.getSuccessfulTransactions().subscribe(
          (data) => {
            this.transactions = data;
          },
          (error) => {
            console.error('Error fetching successful transactions:', error);
            //this.errorMessage = 'Failed to load transactions. Please try again later.';
          }
        );
        break;
      case 'getFailed':
        this.transactionService.getFailedTransactions().subscribe(
          (data) => {
            this.transactions = data;
          },
          (error) => {
            console.error('Error fetching failed transactions:', error);
            //this.errorMessage = 'Failed to load transactions. Please try again later.';
          }
        );
        break;
    }
  }
 
  // Add a new transaction
  addTransaction(): void {
    this.transactionService.addTransaction(this.newTransaction).subscribe(
      (data) => {
        console.log('Transaction added successfully', data);
        console.log(this.newTransaction);
        this.newTransaction = {} as Transactions;
        this.showAddTransactionForm = false;
        this.getTransactions();
      },
      (error) => console.error('Error adding transaction:', error)
    );
  }
 
  toggleAddTransactionForm(): void {
    this.showAddTransactionForm = !this.showAddTransactionForm;
  }
 
  // Enable edit mode for a specific transaction
  editTransaction(index: number): void {
    this.editTransactionIndex = index;
  }
 
  // Save changes to transaction details
  saveTransaction(index: number): void {
    const updateTransaction = this.transactions[index];
    this.transactionService.updateTransaction(updateTransaction.transactionId, updateTransaction).subscribe(
      () => {
        this.editTransactionIndex = null;
      },
      (error) => console.error('Error updating transaction:', error)
    );
  }
 
  // Cancel edit and revert changes
  cancelEdit(): void {
    this.editTransactionIndex = null;
  }
}
 