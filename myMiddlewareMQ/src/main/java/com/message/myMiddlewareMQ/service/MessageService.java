package com.message.myMiddlewareMQ.service;

import com.message.myMiddlewareMQ.exceptions.ResourceNotFoundException;
import com.message.myMiddlewareMQ.model.MessageEntity;
import com.message.myMiddlewareMQ.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    public MessageEntity saveMessage(MessageEntity message){
        return messageRepository.save(message);
    } public List<MessageEntity> getAllMessages() {
        return messageRepository.findAll();
    } public MessageEntity getMessageById(Long id) {
        messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found"));
    }

}
