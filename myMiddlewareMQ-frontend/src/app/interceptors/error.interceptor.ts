import { Injectable } from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor,
    HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { MessageService } from 'primeng/api';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(private messageService: MessageService) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(
            catchError((error: HttpErrorResponse) => {
                let errorMessage = 'An error occurred';
                
                if (error.error instanceof ErrorEvent) {
                    // Client-side error
                    errorMessage = error.error.message;
                } else {
                    // Server-side error
                    errorMessage = error.error?.message || `Error Code: ${error.status}\nMessage: ${error.message}`;
                }

                this.messageService.add({
                    severity: 'error',
                    summary: 'Error',
                    detail: errorMessage,
                    life: 5000
                });

                return throwError(() => error);
            })
        );
    }
}
