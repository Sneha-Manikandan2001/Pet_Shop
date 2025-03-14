import { Injectable } from '@angular/core';
import {  HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FoodRelationship, GroomingRelationship, Pet, SupplierRelationship, VaccinationRelationship } from '../models/Pets';
import { Transactions } from '../models/Transaction';
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
 
  getGroomingServicesByPetId(petId: number): Observable<GroomingRelationship[]> {
    return this.http.get<GroomingRelationship[]>(`${this.baseUrl}/grooming_services/${petId}`);
  }
 
  getVaccinationsByPetId(petId: number): Observable<VaccinationRelationship[]> {
    return this.http.get<VaccinationRelationship[]>(`${this.baseUrl}/vaccinations/${petId}`);
  }
 
 
 
  getFoodInfoByPetId(petId: number): Observable<FoodRelationship[]> {
    return this.http.get<FoodRelationship[]>(`${this.baseUrl}/food_info/${petId}`);
  }
 
  getSuppliersByPetId(petId: number): Observable<SupplierRelationship[]> {
    return this.http.get<SupplierRelationship[]>(`${this.baseUrl}/suppliers/${petId}`);
  }
 
  getTransactionHistoryByPetId(pet_id: number): Observable<Transactions[]> {
    return this.http.get<Transactions[]>(`${this.baseUrl}/transaction_history/${pet_id}`);
  }
}
 