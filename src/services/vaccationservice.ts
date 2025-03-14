import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vaccinations } from '../models/Vaccinations';

@Injectable({
  providedIn: 'root'
})
export class VaccinationService {
  private apiUrl = 'http://localhost:8080/api/v1/vaccinations';

  constructor(private http: HttpClient) {}

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

  getAllVaccinations(): Observable<Vaccinations[]> {
    const headers = this.getHeaders();
    return this.http.get<Vaccinations[]>(this.apiUrl, { headers });
  }

  getVaccinationById(vaccinationId: number): Observable<Vaccinations> {
    const headers = this.getHeaders();
    return this.http.get<Vaccinations>(`${this.apiUrl}/${vaccinationId}`, { headers });
  }

  getAvailableVaccinations(): Observable<Vaccinations[]> {
    const headers = this.getHeaders();
    return this.http.get<Vaccinations[]>(`${this.apiUrl}/available`, { headers });
  }

  getUnavailableVaccinations(): Observable<Vaccinations[]> {
    const headers = this.getHeaders();
    return this.http.get<Vaccinations[]>(`${this.apiUrl}/unavailable`, { headers });
  }

  addVaccination(vaccination: Vaccinations): Observable<any> {
    const headers = this.getHeaders();
    return this.http.post<any>(`${this.apiUrl}/add`, vaccination, { headers });
  }

  updateVaccination(vaccinationId: number, vaccination: Vaccinations): Observable<any> {
    const headers = this.getHeaders();
    return this.http.put<any>(`${this.apiUrl}/update/${vaccinationId}`, vaccination, { headers });
  }
}