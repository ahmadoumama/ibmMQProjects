import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Message } from '../models/message.interface';
import { environment } from '../../environments/environment';
import { LoadingService } from './loading.service';
import { finalize, tap } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class MessageService {
    private apiUrl = `${environment.apiUrl}/messages`;

    constructor(
        private http: HttpClient,
        private loadingService: LoadingService
    ) { }

    getMessages(): Observable<Message[]> {
        this.loadingService.setLoading(true);
        return this.http.get<Message[]>(this.apiUrl).pipe(
            finalize(() => this.loadingService.setLoading(false))
        );
    }

    getMessageById(id: number): Observable<Message> {
        this.loadingService.setLoading(true);
        return this.http.get<Message>(`${this.apiUrl}/${id}`).pipe(
            finalize(() => this.loadingService.setLoading(false))
        );
    }

    createMessage(message: Partial<Message>): Observable<Message> {
        this.loadingService.setLoading(true);
        return this.http.post<Message>(this.apiUrl, message).pipe(
            finalize(() => this.loadingService.setLoading(false))
        );
    }
}
