import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Message } from '../../models/message.interface';
import { MessageService } from '../../services/message.service';

@Component({
  selector: 'app-message-list',
  templateUrl: './message-list.component.html',
  styleUrls: ['./message-list.component.scss']
})
export class MessageListComponent implements OnInit {
  messages: Message[] = [];

  constructor(
    private messageService: MessageService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadMessages();
  }

  loadMessages() {
    this.messageService.getMessages().subscribe({
      next: (data) => {
        this.messages = data;
      },
      error: (error) => {
        console.error('Error loading messages:', error);
      }
    });
  }

  onRowSelect(message: Message) {
    this.router.navigate(['/message', message.id]);
  }
}
