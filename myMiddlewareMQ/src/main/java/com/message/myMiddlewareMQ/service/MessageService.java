package com.message.myMiddlewareMQ.service;

import com.message.myMiddlewareMQ.dto.MessageDTO;
import com.message.myMiddlewareMQ.exceptions.MessageProcessingException;
import com.message.myMiddlewareMQ.exceptions.ResourceNotFoundException;
import com.message.myMiddlewareMQ.mapper.MessageMapper;
import com.message.myMiddlewareMQ.model.MessageEntity;
import com.message.myMiddlewareMQ.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling message operations in the middleware MQ system.
 * This service provides methods for saving and retrieving messages using the message repository.
 * 
 * Last modified: 2024-12-16 11:53:59 +01:00
 */
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private MessageMapper messageMapper;

    public MessageDTO saveMessage(MessageDTO messageDTO) {
        try {
            if (messageDTO == null) {
                throw new MessageProcessingException("Message cannot be null");
            }
            MessageEntity entity = messageMapper.toEntity(messageDTO);
            MessageEntity savedEntity = messageRepository.save(entity);
            return messageMapper.toDto(savedEntity);
        } catch (Exception e) {
            throw new MessageProcessingException("Error saving message", e);
        }
    }

    public List<MessageDTO> getAllMessages() {
        try {
            return messageRepository.findAll().stream()
                .map(messageMapper::toDto)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new MessageProcessingException("Error retrieving messages", e);
        }
    }

    public MessageDTO getMessageById(Long id) {
        if (id == null) {
            throw new MessageProcessingException("Message ID cannot be null");
        }
        try {
            return messageRepository.findById(id)
                .map(messageMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + id));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new MessageProcessingException("Error retrieving message with id: " + id, e);
        }
    }
}
