import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MessageListComponent } from './components/message-list/message-list.component';
import { MessageDetailComponent } from './components/message-detail/message-detail.component';

const routes: Routes = [
  { path: '', redirectTo: 'messages', pathMatch: 'full' },
  { path: 'messages', component: MessageListComponent },
  { path: 'message/:id', component: MessageDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

