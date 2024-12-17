package com.message.myMiddlewareMQ.service;

import com.message.myMiddlewareMQ.dto.MessageDTO;
import com.message.myMiddlewareMQ.exceptions.MessageProcessingException;
import com.message.myMiddlewareMQ.exceptions.ResourceNotFoundException;
import com.message.myMiddlewareMQ.mapper.MessageMapper;
import com.message.myMiddlewareMQ.model.MessageEntity;
import com.message.myMiddlewareMQ.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private MessageMapper messageMapper;

    @InjectMocks
    private MessageService messageService;

    private MessageDTO messageDTO;
    private MessageEntity messageEntity;

    @BeforeEach
    void setUp() {
        messageDTO = new MessageDTO();
        messageDTO.setId(1L);
        messageDTO.setPayload("Test Message");
        messageDTO.setReceivedAt(LocalDateTime.now());

        messageEntity = new MessageEntity();
        messageEntity.setId(1L);
        messageEntity.setPayload("Test Message");
        messageEntity.setReceivedAt(LocalDateTime.now());
    }

    @Test
    void saveMessage_Success() {
        when(messageMapper.toEntity(any(MessageDTO.class))).thenReturn(messageEntity);
        when(messageRepository.save(any(MessageEntity.class))).thenReturn(messageEntity);
        when(messageMapper.toDto(any(MessageEntity.class))).thenReturn(messageDTO);

        MessageDTO result = messageService.saveMessage(messageDTO);

        assertNotNull(result);
        assertEquals(messageDTO.getId(), result.getId());
        assertEquals(messageDTO.getPayload(), result.getPayload());
        verify(messageRepository).save(any(MessageEntity.class));
    }

    @Test
    void saveMessage_NullMessage_ThrowsException() {
        assertThrows(MessageProcessingException.class, () -> messageService.saveMessage(null));
    }

    @Test
    void getAllMessages_Success() {
        List<MessageEntity> entities = Arrays.asList(messageEntity);
        when(messageRepository.findAll()).thenReturn(entities);
        when(messageMapper.toDto(any(MessageEntity.class))).thenReturn(messageDTO);

        List<MessageDTO> results = messageService.getAllMessages();

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        verify(messageRepository).findAll();
    }

    @Test
    void getMessageById_Success() {
        when(messageRepository.findById(1L)).thenReturn(Optional.of(messageEntity));
        when(messageMapper.toDto(any(MessageEntity.class))).thenReturn(messageDTO);

        MessageDTO result = messageService.getMessageById(1L);

        assertNotNull(result);
        assertEquals(messageDTO.getId(), result.getId());
        verify(messageRepository).findById(1L);
    }

    @Test
    void getMessageById_NotFound_ThrowsException() {
        when(messageRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> messageService.getMessageById(1L));
        verify(messageRepository).findById(1L);
    }

    @Test
    void getMessageById_NullId_ThrowsException() {
        assertThrows(MessageProcessingException.class, () -> messageService.getMessageById(null));
    }
}
