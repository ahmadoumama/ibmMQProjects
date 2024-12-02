import { Component, Input } from '@angular/core';


@Component({
  selector: 'app-message-detail',
  templateUrl: './message-detail.component.html',
  styleUrl: './message-detail.component.scss'
})

@Component({
  selector: 'app-message-detail',
  templateUrl: './message-detail.component.html'
})
export class MessageDetailComponent {
  @Input() message: any;

  closeDetail(): void {
    this.message = null;
  }
}
