import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Transactions } from '../models/pets';

import { TransactionStatus } from '../models/pets';
 
@Injectable({

  providedIn: 'root'

})

export class TransactionService {

  private baseUrl = 'http://localhost:8080/api/v1/transaction_history';
 
  public getToken(): string | null {

    return localStorage.getItem('token');

  }
 
  constructor(private http: HttpClient) {}
 
 
  addTransaction(transaction: Transactions): Observable<any> {

    let token = this.getToken();

    const headers = new HttpHeaders({

      'Authorization': `Bearer ${token}`,

    });

    return this.http.post<any>(`${this.baseUrl}/add`, transaction, { headers });

  }
 
  

}
 
 
