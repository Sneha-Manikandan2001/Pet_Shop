import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PetFood } from '../models/PetFood';

@Injectable({
  providedIn: 'root'
})
export class PetFoodService {

  private baseUrl = 'http://localhost:8080/api/v1/pet_foods';

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

  getAllPetFoods(): Observable<PetFood[]> {
    const headers = this.getHeaders();
    return this.http.get<PetFood[]>(`${this.baseUrl}`, { headers });
  }

  getPetFoodById(foodId: number): Observable<PetFood> {
    const headers = this.getHeaders();
    return this.http.get<PetFood>(`${this.baseUrl}/${foodId}`, { headers });
  }

  searchPetFoodsByName(name: string): Observable<PetFood[]> {
    const headers = this.getHeaders();
    return this.http.get<PetFood[]>(`${this.baseUrl}/search?name=${name}`, { headers });
  }

  getPetFoodsByType(type: string): Observable<PetFood[]> {
    const headers = this.getHeaders();
    return this.http.get<PetFood[]>(`${this.baseUrl}/food_type/${type}`, { headers });
  }

  getPetFoodsByBrand(brandName: string): Observable<PetFood[]> {
    const headers = this.getHeaders();
    return this.http.get<PetFood[]>(`${this.baseUrl}/brand/${brandName}`, { headers });
  }

  addPetFood(petFood: PetFood): Observable<PetFood> {
    const headers = this.getHeaders();
    return this.http.post<PetFood>(`${this.baseUrl}/add`, petFood, { headers });
  }

  updatePetFood(foodId: number, petFood: PetFood): Observable<PetFood> {
    const headers = this.getHeaders();
    return this.http.put<PetFood>(`${this.baseUrl}/${foodId}`, petFood, { headers });
  }

  updatePetFoodQuantity(foodId: number, quantity: number): Observable<PetFood> {
    const headers = this.getHeaders();
    return this.http.patch<PetFood>(`${this.baseUrl}/${foodId}/quantity`, { quantity }, { headers });
  }
}