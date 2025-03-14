import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employees } from '../models/Employees';

@Injectable({
  providedIn: 'root'
})
export class EmployeesService {
  private baseUrl = 'http://localhost:8080/api/v1/employees';

  public getToken(): string | null {
    return localStorage.getItem('token');
  }

  constructor(private http: HttpClient) {}

  getAllEmployees(): Observable<Employees[]> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.get<Employees[]>(`${this.baseUrl}`, { headers });
  }

  getEmployeeById(employeeId: number): Observable<Employees> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.get<Employees>(`${this.baseUrl}/${employeeId}`, { headers });
  }

  getEmployeesByFirstName(firstName: string): Observable<Employees[]> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.get<Employees[]>(`${this.baseUrl}/firstname/${firstName}`, { headers });
  }

  getEmployeesByPosition(position: string): Observable<Employees[]> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.get<Employees[]>(`${this.baseUrl}/position/${position}`, { headers });
  }

  addEmployee(employee: Employees): Observable<Employees> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.post<Employees>(`${this.baseUrl}/add`, employee, { headers });
  }

  updateEmployee(employeeId: number, employee: Employees): Observable<Employees> {
    let token = this.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
    });
    return this.http.put<Employees>(`${this.baseUrl}/update/${employeeId}`, employee, { headers });
  }
}