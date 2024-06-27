import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { SessionService } from "src/app/core/services/session.service";
import { Router } from "@angular/router";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

    public constructor(private sessionService: SessionService, private router: Router) { }

    public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (!req.url.includes("/auth")) {
            try {
                const token = this.sessionService.getToken();
                if (token) {
                    const headers = new HttpHeaders().append("Authorization", `Bearer ${token}`);
                    const modifiedRequest = req.clone({ headers });

                    return next.handle(modifiedRequest);
                }
            } catch (error) {
                alert("Votre session à expiré, veuillez vous reconnecter.");
                this.router.navigateByUrl("/auth/login");
            }
        }
        return next.handle(req);

    }
}