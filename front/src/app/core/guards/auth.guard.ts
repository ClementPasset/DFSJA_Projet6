import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { SessionService } from "../services/session.service";

@Injectable({
    providedIn: "root"
})
export class AuthGuard implements CanActivate {

    public constructor(private sessionService: SessionService, private router: Router) { }

    public canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        let token = "";
        try {
            token = this.sessionService.getToken();
            if (token !== "") {
                if (state.url.includes("/auth") || state.url === "/") {
                    this.router.navigateByUrl("/post");
                }
                return true;
            }
            this.router.navigateByUrl("/auth/login");
            return false;
        } catch (error) {
            if (!(state.url.includes("/auth") || state.url == "/")) {
                this.router.navigateByUrl("/auth/login");
                return false;
            } else {
                return true;
            }
        }
    }

}