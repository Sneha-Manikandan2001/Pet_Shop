import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vaccinations } from '../../models/vaccinations';

@Injectable({
  providedIn: 'root'
})
export class VaccinationService {
  private apiUrl = 'http://localhost:8080/api/v1/vaccinations';

  constructor(private http: HttpClient) {}

  getAllVaccinations(): Observable<Vaccinations[]> {
    return this.http.get<Vaccinations[]>(this.apiUrl);
  }

  getVaccinationById(vaccinationId: number): Observable<Vaccinations> {
    return this.http.get<Vaccinations>(`${this.apiUrl}/${vaccinationId}`);
  }

  getAvailableVaccinations(): Observable<Vaccinations[]> {
    return this.http.get<Vaccinations[]>(`${this.apiUrl}/available`);
  }

  getUnavailableVaccinations(): Observable<Vaccinations[]> {
    return this.http.get<Vaccinations[]>(`${this.apiUrl}/unavailable`);
  }

  addVaccination(vaccination: Vaccinations): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<any>(`${this.apiUrl}/add`, vaccination, { headers });
  }

  updateVaccination(vaccinationId: number, vaccination: Vaccinations): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<any>(`${this.apiUrl}/update/${vaccinationId}`, vaccination, { headers });
  }
}