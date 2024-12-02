import { Component, OnInit } from '@angular/core';
import { MessageService } from '../../services/message.service';


@Component({
  selector: 'app-message-list',
  templateUrl: './message-list.component.html',
  styleUrl: './message-list.component.scss'
})
export class MessageListComponent implements OnInit {
  messages: any[] = [];

  constructor(private messageService: MessageService) { }

  ngOnInit(): void {
    this.messageService.getMessages().subscribe(data => {
      this.messages = data;
    });
  }
}

