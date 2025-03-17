import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customers } from '../models/pets';

@Injectable({
  providedIn: 'root'
})

export class CustomersService {
  private baseUrl = 'http://localhost:8080/api/v1/customers';
 
  constructor(private http: HttpClient) { }
 
  getCustomerByName(first_name: string, last_name: string): Observable<Customers> {
    return this.http.get<Customers>(`${this.baseUrl}/name/${first_name}/${last_name}`);
  }
 

}

