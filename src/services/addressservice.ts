import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Addresses } from '../models/addresses';

@Injectable({
  providedIn: 'root'
})
export class AddressesService {
  private baseUrl = 'http://localhost:8080/api/v1/address';

  constructor(private http: HttpClient) { }

  private getToken(): string | null {
    return localStorage.getItem('token');
  }

  private getHeaders(): HttpHeaders {
    const token = this.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }

  addAddress(address: Addresses): Observable<any> {
    const headers = this.getHeaders();
    return this.http.post(`${this.baseUrl}/add`, address, { headers });
  }

  getAllAddresses(): Observable<Addresses[]> {
    const headers = this.getHeaders();
    return this.http.get<Addresses[]>(this.baseUrl, { headers });
  }

  getAddressById(addressId: number): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get(`${this.baseUrl}/${addressId}`, { headers });
  }

  getAddressesByCity(city: string): Observable<Addresses[]> {
    const headers = this.getHeaders();
    return this.http.get<Addresses[]>(`${this.baseUrl}/city/${city}`, { headers });
  }

  getAddressesByState(state: string): Observable<Addresses[]> {
    const headers = this.getHeaders();
    return this.http.get<Addresses[]>(`${this.baseUrl}/state/${state}`, { headers });
  }

  updateAddress(addressId: number, addressDetails: Addresses): Observable<Addresses> {
    const headers = this.getHeaders();
    return this.http.put<Addresses>(`${this.baseUrl}/update/${addressId}`, addressDetails, { headers });
  }
}