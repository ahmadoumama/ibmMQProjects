import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from '../../models/message.interface';
import { MessageService } from '../../services/message.service';

@Component({
  selector: 'app-message-detail',
  templateUrl: './message-detail.component.html',
  styleUrls: ['./message-detail.component.scss']
})
export class MessageDetailComponent implements OnInit {
  message: Message | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.messageService.getMessageById(Number(id)).subscribe({
        next: (data) => {
          this.message = data;
        },
        error: (error) => {
          console.error('Error loading message:', error);
          this.goBack();
        }
      });
    }
  }

  goBack() {
    this.router.navigate(['/messages']);
  }
}
