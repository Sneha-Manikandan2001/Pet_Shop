import { Injectable } from '@angular/core';
import {  HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FoodRelationship, GroomingRelationship, Pet, SupplierRelationship, Transactions, VaccinationRelationship } from '../../models/pets';
 
@Injectable({
  providedIn: 'root'
})
export class PetService {
  private baseUrl = 'http://localhost:8080/api/v1/pets';
 
  constructor(private http: HttpClient) {}
 
  getAllPets(): Observable<Pet[]> {
    return this.http.get<Pet[]>(`${this.baseUrl}`);
  }
 
  getPetById(petId: number): Observable<Pet> {
    return this.http.get<Pet>(`${this.baseUrl}/${petId}`);
  }
 
  getPetsByCategory(category: string): Observable<Pet[]> {
    return this.http.get<Pet[]>(`${this.baseUrl}/category/${category}`);
  }
 
  addPet(pet: Pet): Observable<any> {
    
  return this.http.post(`${this.baseUrl}/add`, pet)
  }
 
  updatePet(petId: number, petDetails: Pet): Observable<any> {
    return this.http.put(`${this.baseUrl}/update/${petId}`, petDetails);
  }
 
  getGroomingServicesByPetId(pet_id: number): Observable<GroomingRelationship[]> {
    return this.http.get<GroomingRelationship[]>(`${this.baseUrl}/grooming_services/${pet_id}`);
  }
 
  getVaccinationsByPetId(pet_id: number): Observable<VaccinationRelationship[]> {
    return this.http.get<VaccinationRelationship[]>(`${this.baseUrl}/vaccinations/${pet_id}`);
  }
 
  getFoodInfoByPetId(pet_id: number): Observable<FoodRelationship[]> {
    return this.http.get<FoodRelationship[]>(`${this.baseUrl}/food_info/${pet_id}`);
  }
 
  getSuppliersByPetId(pet_id: number): Observable<SupplierRelationship[]> {
    return this.http.get<SupplierRelationship[]>(`${this.baseUrl}/suppliers/${pet_id}`);
  }
 
  getTransactionHistoryByPetId(pet_id: number): Observable<Transactions[]> {
    return this.http.get<Transactions[]>(`${this.baseUrl}/transaction_history/${pet_id}`);
  }
}
 