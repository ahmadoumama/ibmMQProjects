package com.message.myMiddlewareMQ.service;

import com.message.myMiddlewareMQ.dto.PartnerDTO;
import com.message.myMiddlewareMQ.exceptions.MessageProcessingException;
import com.message.myMiddlewareMQ.mapper.PartnerMapper;
import com.message.myMiddlewareMQ.model.PartnerEntity;
import com.message.myMiddlewareMQ.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling partner operations in the middleware MQ system.
 * This service provides methods for managing partners using the partner repository.
 * 
 * Last modified: 2024-12-16 11:53:59 +01:00
 */
@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;
    
    @Autowired
    private PartnerMapper partnerMapper;

    public PartnerDTO addPartner(PartnerDTO partnerDTO) {
        try {
            if (partnerDTO == null) {
                throw new MessageProcessingException("Partner cannot be null");
            }
            PartnerEntity entity = partnerMapper.toEntity(partnerDTO);
            PartnerEntity savedEntity = partnerRepository.save(entity);
            return partnerMapper.toDto(savedEntity);
        } catch (Exception e) {
            throw new MessageProcessingException("Error saving partner", e);
        }
    }

    public void deletePartner(Long id) {
        try {
            if (!existsByIdPartner(id)) {
                throw new MessageProcessingException("Partner not found with id: " + id);
            }
            partnerRepository.deleteById(id);
        } catch (Exception e) {
            throw new MessageProcessingException("Error deleting partner with id: " + id, e);
        }
    }

    public boolean existsByIdPartner(Long id) {
        try {
            return partnerRepository.existsById(id);
        } catch (Exception e) {
            throw new MessageProcessingException("Error checking partner existence with id: " + id, e);
        }
    }

    public List<PartnerDTO> getAllPartner() {
        try {
            return partnerRepository.findAll().stream()
                .map(partnerMapper::toDto)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new MessageProcessingException("Error retrieving partners", e);
        }
    }
}
