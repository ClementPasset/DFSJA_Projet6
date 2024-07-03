import { Injectable } from '@angular/core';
import { LoginResponse } from '../../auth/models/LoginResponse.interface';
import { BehaviorSubject, Observable, catchError, map, of, tap } from 'rxjs';
import { UserInformation } from '../models/user-information.model';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable()
export class SessionService {

  private _userInfo$ = new BehaviorSubject<UserInformation>(new UserInformation("", ""));
  get userInfo$(): Observable<UserInformation> {
    return this._userInfo$.asObservable();
  }

  public constructor(private http: HttpClient) { }

  private getTokenFromStorage(): string {
    const token = localStorage.getItem("mdd_token");
    if (token) {
      return token;
    } else {
      throw new Error("Expired user session.");
    }
  }

  private isTokenValid(): boolean {
    const expiration = localStorage.getItem("mdd_tokenExpiration");
    if (expiration) {
      const expirationDate = new Date(expiration);
      const currentDate = new Date();

      return expirationDate > currentDate
    }
    return false;
  }

  public storeUserInformations(loginResponse: LoginResponse): void {
    localStorage.setItem("mdd_token", loginResponse.token);
    localStorage.setItem("mdd_tokenExpiration", loginResponse.expirationDate);
    localStorage.setItem("mdd_userEmail", loginResponse.email);
    localStorage.setItem("mdd_userUsername", loginResponse.username);
  }

  public clearUserInformations(): void {
    localStorage.setItem("mdd_token", "");
    localStorage.setItem("mdd_tokenExpiration", "");
    localStorage.setItem("mdd_userEmail", "");
    localStorage.setItem("mdd_userUsername", "");
  }

  public getToken(): string {
    if (!this.isTokenValid()) {
      this.clearUserInformations();
      throw new Error("Expired user session.");
    } else {
      return this.getTokenFromStorage();
    }
  }

  public getUserInfos(): void {
    this._userInfo$.next(new UserInformation(localStorage.getItem("mdd_userUsername") ?? "", localStorage.getItem("mdd_userEmail") ?? ""));
  }

  public updateUserInfos(userInfo: UserInformation): Observable<boolean> {
    return this.http.put<LoginResponse>(`${environment.baseUrl}/user`, userInfo).pipe(
      tap(response => {
        this.storeUserInformations(response);
      }),
      map(() => true),
      catchError(() => of(false))
    );
  }
}
