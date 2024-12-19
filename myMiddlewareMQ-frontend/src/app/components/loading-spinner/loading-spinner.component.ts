import { Component } from '@angular/core';
import { LoadingService } from '../../services/loading.service';

@Component({
    selector: 'app-loading-spinner',
    template: `
        <div class="loading-spinner" *ngIf="loadingService.getLoading() | async">
            <p-progressSpinner 
                strokeWidth="4" 
                [style]="{width: '50px', height: '50px'}"
                animationDuration=".5s">
            </p-progressSpinner>
        </div>
    `,
    styles: [`
        .loading-spinner {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            display: flex;
            justify-content: center;
            align-items: center;
            z-index: 9999;
        }
    `]
})
export class LoadingSpinnerComponent {
    constructor(public loadingService: LoadingService) {}
}
