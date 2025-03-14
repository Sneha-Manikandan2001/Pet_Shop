import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GroomingServices } from '../../models/GroomingServices';
 
@Injectable({
  providedIn: 'root'
})
export class GroomingServicesService {
  private baseUrl = 'http://localhost:8080/api/v1/services';
 
  constructor(private http: HttpClient) { }
 
  private getToken(): string | null {
    return localStorage.getItem('token');
  }
 
  private getHeaders(): HttpHeaders {
    const token = this.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }
 
  getAllGroomingServices(): Observable<GroomingServices[]> {
    const headers = this.getHeaders();
    return this.http.get<GroomingServices[]>(`${this.baseUrl}`, { headers });
  }
 
  getGroomingServiceById(serviceId: number): Observable<GroomingServices> {
    const headers = this.getHeaders();
    return this.http.get<GroomingServices>(`${this.baseUrl}/${serviceId}`, { headers });
  }
 
  getAvailableGroomingServices(): Observable<GroomingServices[]> {
    const headers = this.getHeaders();
    return this.http.get<GroomingServices[]>(`${this.baseUrl}/available`, { headers });
  }
 
  getUnavailableGroomingServices(): Observable<GroomingServices[]> {
    const headers = this.getHeaders();
    return this.http.get<GroomingServices[]>(`${this.baseUrl}/unavailable`, { headers });
  }
 
 
}