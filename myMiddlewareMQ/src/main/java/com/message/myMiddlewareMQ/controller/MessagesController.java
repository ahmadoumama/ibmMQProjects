package com.message.myMiddlewareMQ.controller;

import com.message.myMiddlewareMQ.model.MessageEntity;
import com.message.myMiddlewareMQ.repository.MessageRepository;
import com.message.myMiddlewareMQ.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {
    @Autowired
    private MessageService messageService;

    @GetMapping public ResponseEntity<List<MessageEntity>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }
    @GetMapping("/{id}")
    public ResponseEntity<MessageEntity> getMessageById(@PathVariable Long id) {
        return ResponseEntity.ok(messageService.getMessageById(id));

    }
}

