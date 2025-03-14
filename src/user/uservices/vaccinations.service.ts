import { Injectable } from '@angular/core';
import { Vaccinations } from '../../models/Vaccinations';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VaccinationsService {

  constructor(private httpClient: HttpClient) {}
  baseUrl: string = 'http://localhost:8080/api/v1/vaccinations'; 

  getAllVaccinations(): Observable<Vaccinations[]> {
    return this.httpClient.get<Vaccinations[]>(this.baseUrl);
  }
}
