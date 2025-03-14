import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customers } from '../models/Customer';
import { Addresses } from '../models/addresses';
 
@Injectable({
  providedIn: 'root'
})
export class CustomersService {
  private baseUrl = 'http://localhost:8080/api/v1/customers';
 
  constructor(private http: HttpClient) { }
 
  addCustomer(customer: Customers): Observable<any> {
    return this.http.post(this.baseUrl + "/add", customer, {
      responseType: 'json'
    });
  }
 
  getAllCustomers(): Observable<Customers[]> {
    return this.http.get<Customers[]>(this.baseUrl);
  }
 
  getCustomerById(customerId: number): Observable<Customers> {
    return this.http.get<Customers>(`${this.baseUrl}/${customerId}`);
  }
 
  getCustomerByName(first_name: string, last_name: string): Observable<Customers> {
    return this.http.get<Customers>(`${this.baseUrl}/name/${first_name}/${last_name}`);
  }
 
  getCustomersByCity(city: string): Observable<Customers[]> {
    return this.http.get<Customers[]>(`${this.baseUrl}/by_city/${city}`);
  }
 
  getCustomersByState(state: string): Observable<Customers[]> {
    return this.http.get<Customers[]>(`${this.baseUrl}/by_state/${state}`);
  }
 
  
 
  getCustomersByTransactionStatus(status: string): Observable<Customers[]> {
    return this.http.get<Customers[]>(`${this.baseUrl}/transactions_status/${status}`);
  }
 
  
 
  getPetsByCustomerFirstName(firstName: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/pets/${firstName}`);
  }
 
  updateCustomer(customerId: number, customerDetails: Customers): Observable<Customers> {
    return this.http.put<Customers>(`${this.baseUrl}/update/${customerId}`, customerDetails, {
      responseType: 'json'
    });
  }
}
 