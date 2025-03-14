import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Supplier } from '../models/Supplier';
import { Addresses } from '../models/addresses';

@Injectable({
  providedIn: 'root'
})
export class SupplierService {
  private baseUrl = 'http://localhost:8080/api/v1/suppliers';

  public getToken(): string | null {
    return localStorage.getItem('token');
  }

  constructor(private http: HttpClient) {}

  getAllSuppliers(): Observable<Supplier[]> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.get<Supplier[]>(`${this.baseUrl}`, { headers });
  }

  getSupplierById(supplierId: number): Observable<Supplier> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.get<Supplier>(`${this.baseUrl}/${supplierId}`, { headers });
  }

  getSuppliersByName(name: string): Observable<Supplier[]> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.get<Supplier[]>(`${this.baseUrl}/name/${name}`, { headers });
  }

  getSuppliersByCity(cityName: string): Observable<Supplier[]> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.get<Supplier[]>(`${this.baseUrl}/city/${cityName}`, { headers });
  }

  getSuppliersByState(stateName: string): Observable<Supplier[]> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.get<Supplier[]>(`${this.baseUrl}/state/${stateName}`, { headers });
  }

  addSupplier(supplier: Supplier): Observable<Supplier> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.post<Supplier>(`${this.baseUrl}/add`, supplier, { headers });
  }

  updateSupplier(supplierId: number, supplier: Supplier): Observable<Supplier> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.put<Supplier>(`${this.baseUrl}/update/${supplierId}`, supplier, { headers });
  }
}


