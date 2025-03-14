import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PetCategory } from '../models/PetCategory';

@Injectable({
  providedIn: 'root'
})
export class PetCategoryService {
  private baseUrl = 'http://localhost:8080/api/v1/categories';

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

  getAllCategories(): Observable<PetCategory[]> {
    const headers = this.getHeaders();
    return this.http.get<PetCategory[]>(`${this.baseUrl}`, { headers });
  }

  getCategoryById(categoryId: number): Observable<PetCategory> {
    const headers = this.getHeaders();
    return this.http.get<PetCategory>(`${this.baseUrl}/${categoryId}`, { headers });
  }

  getCategoryByName(categoryName: string): Observable<PetCategory[]> {
    const headers = this.getHeaders();
    return this.http.get<PetCategory[]>(`${this.baseUrl}/name/${categoryName}`, { headers });
  }

  addCategory(category: PetCategory): Observable<string> {
    const headers = this.getHeaders();
    return this.http.post<string>(`${this.baseUrl}/add`, category, { headers });
  }

  updateCategory(categoryId: number, category: PetCategory): Observable<string> {
    const headers = this.getHeaders();
    return this.http.put<string>(`${this.baseUrl}/update/${categoryId}`, category, { headers });
  }
}