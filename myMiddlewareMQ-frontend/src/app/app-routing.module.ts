
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MessageListComponent } from './components/message-list/message-list.component';
import { MessageDetailComponent } from './components/message-detail/message-detail.component';
import { PartnerListComponent } from './components/partner-list/partner-list.component';
import { PartnerFormComponent } from './components/partner-form/partner-form.component';
const routes: Routes = [
  { path: '', redirectTo: '/messages', pathMatch: 'full' },
  { path: 'messages', component: MessageListComponent },
  { path: 'messages/:id', component: MessageDetailComponent },
  { path: 'users', component: PartnerListComponent },
  { path: 'add-user', component: PartnerFormComponent },
  // Ajoutez d'autres routes si nécessaire
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

