import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { RegisterRequest } from "../models/RegisterRequest.model";
import { Observable, catchError, map, of } from "rxjs";
import { environment } from "src/environments/environment";
import { LoginRequest } from "../models/LoginRequest.model";

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) { }

  registerUser = (formValue: RegisterRequest): Observable<boolean> => {
    return this.http.post(`${environment.baseUrl}/auth/register`, formValue).pipe(
      map(() => true),
      catchError(() => of(false))
    );
  }

  loginUser = (formValue: LoginRequest): Observable<boolean> => {
    return this.http.post(`${environment.baseUrl}/auth/login`, formValue).pipe(
      map(() => true),
      catchError(() => of(false))
    );
  }

}
