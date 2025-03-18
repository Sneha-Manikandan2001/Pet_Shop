import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Customers } from '../../../models/Customer';
import { Addresses } from '../../../models/addresses';
import { Transactions } from '../../../models/Transaction';
import { CustomersService } from '../../services/customers.service';
import { AddressesService } from '../../../services/addressservice';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
 
@Injectable({
  providedIn: 'root'
})
 
@Component({
  selector: 'app-customer-register',
  imports: [FormsModule, CommonModule],
  templateUrl: './customer-register.component.html',
  styleUrl: './customer-register.component.css'
})
export class CustomerRegisterComponent implements OnInit {
  customers: Customers[] = [];
  addresses: Addresses[] = [];
  transactions: Transactions[] = [];
  newCustomer: Customers = {} as Customers;
  newAddress: Addresses = {} as Addresses;
  showAddCustomerForm: boolean = false;
  errorMessage: string | null = null;
  getCustomersByTransactionStatus: any;
 
  constructor(
    private customersService: CustomersService,
    private addressesService: AddressesService,
    private router: Router
  ) {}
 
  ngOnInit(): void {
    this.loadAddresses();
  }
 
  loadAddresses(): void {
    this.addressesService.getAllAddresses().subscribe(
      (data: Addresses[]) => {
        this.addresses = data;
      },
      (error: any) => {
        console.error('Error fetching addresses:', error);
      }
    );
  }
 
  toggleAddCustomerForm(): void {
    this.showAddCustomerForm = !this.showAddCustomerForm;
  }
 
  addCustomer(): void {
    this.customersService.addCustomer(this.newCustomer).subscribe(
      (data: any) => {
        console.log('Customer added successfully', data);
        this.newCustomer = {} as Customers;
        this.newAddress = {} as Addresses;
        this.router.navigate(['/user-register']);
      },
      (error: any) => console.error('Error adding customer:', error)
    );
  }
}
 