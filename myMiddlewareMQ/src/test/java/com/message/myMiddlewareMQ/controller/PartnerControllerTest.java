package com.message.myMiddlewareMQ.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.myMiddlewareMQ.dto.PartnerDTO;
import com.message.myMiddlewareMQ.exceptions.MessageProcessingException;
import com.message.myMiddlewareMQ.service.PartnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PartnerController.class)
class PartnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartnerService partnerService;

    @Autowired
    private ObjectMapper objectMapper;

    private PartnerDTO partnerDTO;

    @BeforeEach
    void setUp() {
        partnerDTO = new PartnerDTO();
        partnerDTO.setId(1L);
        partnerDTO.setAlias("Test Partner");
        partnerDTO.setType("INBOUND");
        partnerDTO.setDirection("IN");
        partnerDTO.setProcessedFlowType("MESSAGE");
        partnerDTO.setDescription("Test Description");
    }

    @Test
    void getAllPartners_Success() throws Exception {
        List<PartnerDTO> partners = Arrays.asList(partnerDTO);
        when(partnerService.getAllPartner()).thenReturn(partners);

        mockMvc.perform(get("/api/partners"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(partnerDTO.getId()))
                .andExpect(jsonPath("$[0].alias").value(partnerDTO.getAlias()));
    }

    @Test
    void addPartner_Success() throws Exception {
        when(partnerService.addPartner(any(PartnerDTO.class))).thenReturn(partnerDTO);

        mockMvc.perform(post("/api/partners")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(partnerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(partnerDTO.getId()))
                .andExpect(jsonPath("$.alias").value(partnerDTO.getAlias()));
    }

    @Test
    void addPartner_ValidationError() throws Exception {
        partnerDTO.setAlias(null); // Invalid state

        mockMvc.perform(post("/api/partners")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(partnerDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deletePartner_Success() throws Exception {
        when(partnerService.existsByIdPartner(1L)).thenReturn(true);
        doNothing().when(partnerService).deletePartner(1L);

        mockMvc.perform(delete("/api/partners/1"))
                .andExpect(status().isNoContent());

        verify(partnerService).deletePartner(1L);
    }

    @Test
    void deletePartner_NotFound() throws Exception {
        when(partnerService.existsByIdPartner(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/partners/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletePartner_ProcessingError() throws Exception {
        when(partnerService.existsByIdPartner(1L)).thenReturn(true);
        doThrow(new MessageProcessingException("Processing error"))
                .when(partnerService).deletePartner(1L);

        mockMvc.perform(delete("/api/partners/1"))
                .andExpect(status().isInternalServerError());
    }
}
