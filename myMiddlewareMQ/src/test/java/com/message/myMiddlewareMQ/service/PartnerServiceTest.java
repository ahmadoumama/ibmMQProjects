package com.message.myMiddlewareMQ.service;

import com.message.myMiddlewareMQ.dto.PartnerDTO;
import com.message.myMiddlewareMQ.exceptions.MessageProcessingException;
import com.message.myMiddlewareMQ.mapper.PartnerMapper;
import com.message.myMiddlewareMQ.model.PartnerEntity;
import com.message.myMiddlewareMQ.repository.PartnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PartnerServiceTest {

    @Mock
    private PartnerRepository partnerRepository;

    @Mock
    private PartnerMapper partnerMapper;

    @InjectMocks
    private PartnerService partnerService;

    private PartnerDTO partnerDTO;
    private PartnerEntity partnerEntity;

    @BeforeEach
    void setUp() {
        partnerDTO = new PartnerDTO();
        partnerDTO.setId(1L);
        partnerDTO.setAlias("Test Partner");
        partnerDTO.setType("INBOUND");
        partnerDTO.setDirection("IN");
        partnerDTO.setProcessedFlowType("MESSAGE");
        partnerDTO.setDescription("Test Description");

        partnerEntity = new PartnerEntity();
        partnerEntity.setId(1L);
        partnerEntity.setAlias("Test Partner");
        partnerEntity.setType("INBOUND");
        partnerEntity.setDirection("IN");
        partnerEntity.setProcessedFlowType("MESSAGE");
        partnerEntity.setDescription("Test Description");
    }

    @Test
    void addPartner_Success() {
        when(partnerMapper.toEntity(any(PartnerDTO.class))).thenReturn(partnerEntity);
        when(partnerRepository.save(any(PartnerEntity.class))).thenReturn(partnerEntity);
        when(partnerMapper.toDto(any(PartnerEntity.class))).thenReturn(partnerDTO);

        PartnerDTO result = partnerService.addPartner(partnerDTO);

        assertNotNull(result);
        assertEquals(partnerDTO.getId(), result.getId());
        assertEquals(partnerDTO.getAlias(), result.getAlias());
        verify(partnerRepository).save(any(PartnerEntity.class));
    }

    @Test
    void addPartner_NullPartner_ThrowsException() {
        assertThrows(MessageProcessingException.class, () -> partnerService.addPartner(null));
    }

    @Test
    void deletePartner_Success() {
        when(partnerRepository.existsById(1L)).thenReturn(true);
        doNothing().when(partnerRepository).deleteById(1L);

        partnerService.deletePartner(1L);

        verify(partnerRepository).deleteById(1L);
    }

    @Test
    void deletePartner_NotFound_ThrowsException() {
        when(partnerRepository.existsById(1L)).thenReturn(false);

        assertThrows(MessageProcessingException.class, () -> partnerService.deletePartner(1L));
    }

    @Test
    void existsByIdPartner_Success() {
        when(partnerRepository.existsById(1L)).thenReturn(true);

        boolean result = partnerService.existsByIdPartner(1L);

        assertTrue(result);
        verify(partnerRepository).existsById(1L);
    }

    @Test
    void getAllPartner_Success() {
        List<PartnerEntity> entities = Arrays.asList(partnerEntity);
        when(partnerRepository.findAll()).thenReturn(entities);
        when(partnerMapper.toDto(any(PartnerEntity.class))).thenReturn(partnerDTO);

        List<PartnerDTO> results = partnerService.getAllPartner();

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        verify(partnerRepository).findAll();
    }
}
