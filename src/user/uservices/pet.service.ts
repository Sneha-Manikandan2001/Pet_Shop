import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pet, GroomingRelationship, VaccinationRelationship, FoodRelationship} from '../models/pets';

@Injectable({
  providedIn: 'root'
})
export class PetService {
  private baseUrl = 'http://localhost:8080/api/v1/pets';

  constructor(private http: HttpClient) {}

  getAllPets(): Observable<Pet[]> {
    return this.http.get<Pet[]>(`${this.baseUrl}`);
  }


  
  getGroomingServicesByPetId(petId: number): Observable<GroomingRelationship[]> {
    return this.http.get<GroomingRelationship[]>(`${this.baseUrl}/grooming_services/${petId}`);
  }

  getVaccinationsByPetId(petId: number): Observable<VaccinationRelationship[]> {
    return this.http.get<VaccinationRelationship[]>(`${this.baseUrl}/vaccinations/${petId}`);
  }

  getFoodInfoByPetId(petId: number): Observable<FoodRelationship[]> {
    return this.http.get<FoodRelationship[]>(`${this.baseUrl}/food_info/${petId}`);
  }

  
}
