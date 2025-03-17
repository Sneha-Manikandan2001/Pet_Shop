//
import { Component, OnInit } from '@angular/core';
import { CustomersService } from '../../services/customerservice';
import { Customers } from '../../models/Customer';
import { Addresses } from '../../models/addresses';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Transactions } from '../../models/Transaction';
 
@Component({
  selector: 'app-customers',
  imports: [CommonModule, FormsModule],
  templateUrl: './sample.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomersComponent implements OnInit {
  customers: Customers[] = [];
  transactions: Transactions[] =[];
  newCustomer: Customers = {} as Customers;
  newAddress: Addresses = {} as Addresses;
  showAddCustomerForm: boolean = false;
  editCustomerIndex: number | null = null;
  showEditForm: boolean = false;
  errorMessage: string | null = null;
  filterType: string = 'getAll';
  filterValue: string = '';
  filterFirstName: string = '';  
  filterLastName: string = '';
  getTransactionsByCustomerId: any;
  getCustomersByTransactionStatus: any;
  getCustomersWithoutTransactions: any;
  getPetsByCustomerId: any;
 
 
  constructor(private customersService: CustomersService) {}
 
  ngOnInit(): void {
    //this.getCustomers();
    this.getTransactionsByCustomerId();
    this.getCustomersByTransactionStatus();
    //this.getCustomersWithoutTransactions();
    this.getPetsByCustomerId();
  }
 
  // Fetch customers based on filter type
  getCustomers(): void {
    switch (this.filterType) {
      case 'getAll':
        this.customersService.getAllCustomers().subscribe(
          (data: Customers[]) => {
            this.customers = data;
 
           
          },
          (error: any) => {
            console.error('Error fetching customers:', error);
           // this.errorMessage = 'Failed to load customers. Please try again later.';
          }
        );
        break;
      case 'getById':
        this.customersService.getCustomerById(Number(this.filterValue)).subscribe(
          (data: Customers) => {
            this.customers = [data];
            this.customers[0].firstName=this.filterFirstName
            this.customers[0].lastName=this.filterLastName
          },
          (error: HttpErrorResponse) => {
            if (error.status === 404) {
              console.error('Customer not found:', error);
            //  this.errorMessage = 'Customer not found. Please check the ID and try again.';
            } else {
              console.error('Error fetching customer by ID:', error);
              //this.errorMessage = 'Failed to load customer. Please try again later.';
            }
          }
        );
        break;
      case 'getByName':
        const [first_name, last_name] = this.filterValue.split(" ");
         console.log(this.filterFirstName,this.filterLastName)
        this.customersService.getCustomerByName(this.filterFirstName, this.filterLastName).subscribe(
          (data: Customers) => {
            this.customers = [data];
            this.customers[0].firstName=this.filterFirstName
            this.customers[0].lastName=this.filterLastName
          },
          (error: any) => {
            console.error('Error fetching customers by name:', error);
           // this.errorMessage = 'Failed to load customers. Please try again later.';
          }
        );
        break;
      case 'getByCity':
        this.customersService.getCustomersByCity(this.filterValue).subscribe(
          (data: Customers[]) => {
            this.customers = data;
          },
          (error: any) => {
            console.error('Error fetching customers by city:', error);
           // this.errorMessage = 'Failed to load customers. Please try again later.';
          }
        );
        break;
      case 'getByState':
        this.customersService.getCustomersByState(this.filterValue).subscribe(
          (data: Customers[]) => {
            this.customers = data;
          },
          (error: any) => {
            console.error('Error fetching customers by state:', error);
           // this.errorMessage = 'Failed to load customers. Please try again later.';
          }
        );
        break;
      // case 'getTransactionsByCustomerId':
      //   this.customersService.getTransactionsByCustomerId(Number(this.filterValue)).subscribe(
      //     (data: any[]) => {
      //       console.log(data)
      //       // this.customers = data;
      //       this.transactions=data;  
      //       let c:any
      //       this.customers=[]
      //       for(c in this.transactions)
      //       {
      //         this.customers.push( this.transactions[c].customer)
             
 
      //       }
      //     },
      //     (error: any) => {
      //       console.error('Error fetching transactions by customer ID:', error);
      //       this.errorMessage = 'Failed to load transactions. Please try again later.';
      //     }
      //   );
      //   break;
      case 'getCustomersByTransactionStatus':
        this.customersService.getCustomersByTransactionStatus(this.filterValue).subscribe(
          (data: Customers[]) => {
            this.customers = data;
          },
          (error: any) => {
            console.error('Error fetching customers by transaction status:', error);
            //this.errorMessage = 'Failed to load customers. Please try again later.';
          }
        );
        break;
      
      // case 'getPetsByCustomerId':
      //   this.customersService.getPetsByCustomerFirstName(this.filterValue).subscribe(
      //     (data: any[]) => {
      //       this.customers = data;
      //     },
      //     (error: any) => {
      //       console.error('Error fetching pets by customer ID:', error);
      //       this.errorMessage = 'Failed to load pets. Please try again later.';
      //     }
      //   );
      //   break;
    }
  }
 
  // Add a new customer
  addCustomer(): void {
    this.customersService.addCustomer(this.newCustomer).subscribe(
      (data: any) => {
        console.log('Customer added successfully', data);
        this.newCustomer = {} as Customers;
        this.newAddress = {} as Addresses;
        this.showAddCustomerForm = false;
        this.getCustomers();
      },
      (error: any) => console.error('Error adding customer:', error)
    );
  }
 
  toggleAddCustomerForm(): void {
    this.showAddCustomerForm = !this.showAddCustomerForm;
  }
 
 
 
  // Enable edit mode for a specific customer
  editCustomer(index: number): void {
    this.editCustomerIndex = index;
  }
 
  // Save changes to customer details
  saveCustomer(index: number): void {
    const updateCustomer = this.customers[index];
    if (updateCustomer.customerId !== undefined) {
      this.customersService.updateCustomer(updateCustomer.customerId, updateCustomer).subscribe(
        () => {
          this.editCustomerIndex = null;
        },
        (error: any) => console.error('Error updating customer:', error)
      );
    } else {
      console.error('Error: Customer ID is undefined');
    }
  }
 
  // Cancel edit and revert changes
  cancelEdit(): void {
    this.editCustomerIndex = null;
  }
}
 