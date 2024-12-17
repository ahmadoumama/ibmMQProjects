package com.message.myMiddlewareMQ.controller;

import com.message.myMiddlewareMQ.dto.MessageDTO;
import com.message.myMiddlewareMQ.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@Tag(name = "Messages", description = "Message management APIs")
public class MessagesController {
    @Autowired
    private MessageService messageService;

    @GetMapping
    @Operation(summary = "Get all messages", description = "Retrieves a list of all messages in the system")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved messages")
    public ResponseEntity<List<MessageDTO>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get message by ID", description = "Retrieves a specific message by its ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved message")
    @ApiResponse(responseCode = "404", description = "Message not found")
    public ResponseEntity<MessageDTO> getMessageById(@PathVariable Long id) {
        return ResponseEntity.ok(messageService.getMessageById(id));
    }

    @PostMapping
    @Operation(summary = "Create new message", description = "Creates a new message in the system")
    @ApiResponse(responseCode = "201", description = "Message successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid message data")
    public ResponseEntity<MessageDTO> createMessage(@Valid @RequestBody MessageDTO messageDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(messageService.saveMessage(messageDTO));
    }
}
