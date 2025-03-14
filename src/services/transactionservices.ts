import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transactions } from '../models/Transaction';
import { TransactionStatus } from '../models/TransactionStatus';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  private baseUrl = 'http://localhost:8080/api/v1/transaction_history';

  public getToken(): string | null {
    return localStorage.getItem('token');
  }

  constructor(private http: HttpClient) {}

  getAllTransactions(): Observable<Transactions[]> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.get<Transactions[]>(`${this.baseUrl}`, { headers });
  }

  getTransactionById(transactionId: number): Observable<Transactions> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.get<Transactions>(`${this.baseUrl}/${transactionId}`, { headers });
  }

  getTransactionsByCustomerId(customerId: number): Observable<Transactions[]> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.get<Transactions[]>(`${this.baseUrl}/by_customer/${customerId}`, { headers });
  }

  getSuccessfulTransactions(): Observable<Transactions[]> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.get<Transactions[]>(`${this.baseUrl}/successful`, { headers });
  }

  getFailedTransactions(): Observable<Transactions[]> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.get<Transactions[]>(`${this.baseUrl}/failed`, { headers });
  }

  addTransaction(transaction: Transactions): Observable<any> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.post<any>(`${this.baseUrl}/add`, transaction, { headers });
  }

  updateTransaction(transactionId: number, transaction: Transactions): Observable<any> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.put<any>(`${this.baseUrl}/update/${transactionId}`, transaction, { headers });
  }
}


