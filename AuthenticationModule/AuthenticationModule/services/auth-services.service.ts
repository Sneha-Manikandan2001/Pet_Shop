import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
 
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
 
  private RESTURL: string = 'http://localhost:8080/api/';
 
  constructor(private httpClient: HttpClient) { }
 
  public getToken(): string | null {
    return localStorage.getItem('token');
  }
 
  private createHeaders(): HttpHeaders {
    const token = this.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }
 
  UserLogin(user: any): Observable<any> {
    const headers = this.createHeaders();
    return this.httpClient.post(`${this.RESTURL}login`, user, { headers })
      .pipe(
        catchError(this.handleError)
      );
  }
 
  UserRegister(user: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.post(`${this.RESTURL}user/register`, user, { headers })
      .pipe(
        catchError(this.handleError)
      );
  }
 
  AdminLogin(user: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.httpClient.post(`${this.RESTURL}login`, user, { headers })
      .pipe(
        catchError(this.handleError)
      );
  }
 
  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'Unknown error!';
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.error(errorMessage);
    return throwError(errorMessage);
  }
}
 