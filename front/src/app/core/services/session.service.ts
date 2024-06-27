import { Injectable } from '@angular/core';
import { LoginResponse } from '../../auth/models/LoginResponse.interface';

@Injectable()
export class SessionService {

  public constructor() { }

  private getTokenFromStorage(): string {
    const token = localStorage.getItem("mdd_token");
    if (token) {
      return token;
    } else {

    }
    return localStorage.getItem("mdd_token") ?? "";
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
}
