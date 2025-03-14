import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PetFood } from '../../models/PetFood';

@Injectable({
  providedIn: 'root'
})

export class PetFoodService {
  constructor(private http: HttpClient) {}
  private baseUrl: string = 'http://localhost:8080/api/v1/pet_foods';

  getAllPetFoods(): Observable<PetFood[]> {
    return this.http.get<PetFood[]>(this.baseUrl);
  }
  
searchByName(name: string): Observable<PetFood[]> {
  const url = `${this.baseUrl}/search?name=${name}`;
  return this.http.get<PetFood[]>(url);
}
 
  searchByBrand(brand: string): Observable<PetFood[]> {
    const url = `${this.baseUrl}/brand/${brand}`;
    return this.http.get<PetFood[]>(url);
  }
 
  searchByType(type: string): Observable<PetFood[]> {
    const url = `${this.baseUrl}/food_type/${type}`;
    return this.http.get<PetFood[]>(url);
  }
 

}