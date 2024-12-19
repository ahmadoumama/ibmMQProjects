import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';

// PrimeNG Modules
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { CardModule } from 'primeng/card';
import { DialogModule } from 'primeng/dialog';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { ToolbarModule } from 'primeng/toolbar';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';

// Components
import { AppComponent } from './app.component';
import { MessageListComponent } from './components/message-list/message-list.component';
import { MessageDetailComponent } from './components/message-detail/message-detail.component';
import { PartnerListComponent } from './components/partner-list/partner-list.component';
import { PartnerFormComponent } from './components/partner-form/partner-form.component';
import { LoadingSpinnerComponent } from './components/loading-spinner/loading-spinner.component';

// Services and Interceptors
import { ErrorInterceptor } from './interceptors/error.interceptor';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
    declarations: [
        AppComponent,
        MessageListComponent,
        MessageDetailComponent,
        PartnerListComponent,
        PartnerFormComponent,
        LoadingSpinnerComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        BrowserAnimationsModule,
        ReactiveFormsModule,
        // PrimeNG Modules
        TableModule,
        ButtonModule,
        InputTextModule,
        DropdownModule,
        CardModule,
        DialogModule,
        ProgressSpinnerModule,
        ToastModule,
        ToolbarModule,
        ConfirmDialogModule
    ],
    providers: [
        MessageService,
        ConfirmationService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorInterceptor,
            multi: true
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
