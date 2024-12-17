package com.message.myMiddlewareMQ.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.myMiddlewareMQ.dto.MessageDTO;
import com.message.myMiddlewareMQ.exceptions.MessageProcessingException;
import com.message.myMiddlewareMQ.exceptions.ResourceNotFoundException;
import com.message.myMiddlewareMQ.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MessagesController.class)
class MessagesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Autowired
    private ObjectMapper objectMapper;

    private MessageDTO messageDTO;

    @BeforeEach
    void setUp() {
        messageDTO = new MessageDTO();
        messageDTO.setId(1L);
        messageDTO.setPayload("Test Message");
        messageDTO.setReceivedAt(LocalDateTime.now());
    }

    @Test
    void getAllMessages_Success() throws Exception {
        List<MessageDTO> messages = Arrays.asList(messageDTO);
        when(messageService.getAllMessages()).thenReturn(messages);

        mockMvc.perform(get("/api/messages"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(messageDTO.getId()))
                .andExpect(jsonPath("$[0].payload").value(messageDTO.getPayload()));
    }

    @Test
    void getMessageById_Success() throws Exception {
        when(messageService.getMessageById(1L)).thenReturn(messageDTO);

        mockMvc.perform(get("/api/messages/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(messageDTO.getId()))
                .andExpect(jsonPath("$.payload").value(messageDTO.getPayload()));
    }

    @Test
    void getMessageById_NotFound() throws Exception {
        when(messageService.getMessageById(1L)).thenThrow(new ResourceNotFoundException("Message not found"));

        mockMvc.perform(get("/api/messages/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createMessage_Success() throws Exception {
        when(messageService.saveMessage(any(MessageDTO.class))).thenReturn(messageDTO);

        mockMvc.perform(post("/api/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(messageDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(messageDTO.getId()))
                .andExpect(jsonPath("$.payload").value(messageDTO.getPayload()));
    }

    @Test
    void createMessage_ValidationError() throws Exception {
        messageDTO.setPayload(null); // Invalid state

        mockMvc.perform(post("/api/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(messageDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createMessage_ProcessingError() throws Exception {
        when(messageService.saveMessage(any(MessageDTO.class)))
                .thenThrow(new MessageProcessingException("Processing error"));

        mockMvc.perform(post("/api/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(messageDTO)))
                .andExpect(status().isInternalServerError());
    }
}
