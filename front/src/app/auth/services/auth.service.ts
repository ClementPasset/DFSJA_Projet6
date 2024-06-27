import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { RegisterRequest } from "../models/RegisterRequest.model";
import { Observable, catchError, map, of } from "rxjs";
import { environment } from "src/environments/environment";
import { LoginRequest } from "../models/LoginRequest.model";
import { LoginResponse } from "../models/LoginResponse.interface";
import { SessionService } from "../../core/services/session.service";

@Injectable()
export class AuthService {

  constructor(private http: HttpClient, private sessionService: SessionService) { }

  registerUser = (formValue: RegisterRequest): Observable<boolean> => {
    return this.http.post<LoginResponse>(`${environment.baseUrl}/auth/register`, formValue).pipe(
      map(response => {
        this.sessionService.storeUserInformations(response);
        return true;
      }),
      catchError(() => of(false))
    );
  }

  loginUser = (formValue: LoginRequest): Observable<boolean> => {
    return this.http.post<LoginResponse>(`${environment.baseUrl}/auth/login`, formValue).pipe(
      map(response => {
        this.sessionService.storeUserInformations(response);
        return true;
      }),
      catchError(() => of(false))
    );
  }

}
