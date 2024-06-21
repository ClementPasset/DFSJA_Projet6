import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token = sessionStorage.getItem("mdd_jwt");
        const headers = new HttpHeaders().append("Authorization", `Bearer ${token}`);
        const modifiedRequest = req.clone({ headers });

        return next.handle(modifiedRequest);
    }
}