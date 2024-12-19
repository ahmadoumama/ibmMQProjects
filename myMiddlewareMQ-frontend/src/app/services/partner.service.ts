import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Partner } from '../models/partner.interface';
import { environment } from '../../environments/environment';
import { LoadingService } from './loading.service';
import { finalize } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class PartnerService {
    private apiUrl = `${environment.apiUrl}/partners`;

    constructor(
        private http: HttpClient,
        private loadingService: LoadingService
    ) { }

    getPartners(): Observable<Partner[]> {
        this.loadingService.setLoading(true);
        return this.http.get<Partner[]>(this.apiUrl).pipe(
            finalize(() => this.loadingService.setLoading(false))
        );
    }

    getPartnerById(id: number): Observable<Partner> {
        this.loadingService.setLoading(true);
        return this.http.get<Partner>(`${this.apiUrl}/${id}`).pipe(
            finalize(() => this.loadingService.setLoading(false))
        );
    }

    deletePartner(id: number): Observable<void> {
        this.loadingService.setLoading(true);
        return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
            finalize(() => this.loadingService.setLoading(false))
        );
    }

    createPartner(partner: Partial<Partner>): Observable<Partner> {
        this.loadingService.setLoading(true);
        return this.http.post<Partner>(this.apiUrl, partner).pipe(
            finalize(() => this.loadingService.setLoading(false))
        );
    }

    updatePartner(id: number, partner: Partial<Partner>): Observable<Partner> {
        this.loadingService.setLoading(true);
        return this.http.put<Partner>(`${this.apiUrl}/${id}`, partner).pipe(
            finalize(() => this.loadingService.setLoading(false))
        );
    }
}