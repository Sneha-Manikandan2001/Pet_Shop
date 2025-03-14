import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customers } from '../../models/Customer';
import { Addresses } from '../../models/addresses';
 
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
}