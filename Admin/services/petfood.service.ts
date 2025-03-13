import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PetFood } from '../../models/petfood';

@Injectable({
  providedIn: 'root'
})
export class PetFoodService {

  private baseUrl = 'http://localhost:8080/api/v1/pet_foods';

  constructor(private http: HttpClient) { }

  getAllPetFoods(): Observable<PetFood[]> {
    return this.http.get<PetFood[]>(`${this.baseUrl}`);
  }

  getPetFoodById(foodId: number): Observable<PetFood> {
    return this.http.get<PetFood>(`${this.baseUrl}/${foodId}`);
  }

  searchPetFoodsByName(name: string): Observable<PetFood[]> {
    return this.http.get<PetFood[]>(`${this.baseUrl}/search?name=${name}`);
  }

  getPetFoodsByType(type: string): Observable<PetFood[]> {
    return this.http.get<PetFood[]>(`${this.baseUrl}/food_type/${type}`);
  }

  getPetFoodsByBrand(brandName: string): Observable<PetFood[]> {
    return this.http.get<PetFood[]>(`${this.baseUrl}/brand/${brandName}`);
  }

  addPetFood(petFood: PetFood): Observable<PetFood> {
    return this.http.post<PetFood>(`${this.baseUrl}/add`, petFood);
  }

  updatePetFood(foodId: number, petFood: PetFood): Observable<PetFood> {
    return this.http.put<PetFood>(`${this.baseUrl}/${foodId}`, petFood);
  }

  updatePetFoodQuantity(foodId: number, quantity: number): Observable<PetFood> {
    return this.http.patch<PetFood>(`${this.baseUrl}/${foodId}/quantity`, { quantity });
  }
}