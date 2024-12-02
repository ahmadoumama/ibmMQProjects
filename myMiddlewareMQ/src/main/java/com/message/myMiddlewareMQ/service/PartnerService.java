package com.message.myMiddlewareMQ.service;

import com.message.myMiddlewareMQ.model.PartnerEntity;
import com.message.myMiddlewareMQ.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;
    public PartnerEntity addPartner(PartnerEntity partnerEntity){
        return partnerRepository.save(partnerEntity);
    }
    public void deletePartner(Long id) {
        partnerRepository.deleteById(id);
    }
    public boolean existsByIdPartner(Long id) {
      return  partnerRepository.existsById(id);
    }
    public List<PartnerEntity> getAllPartner() {
        return partnerRepository.findAll();
    }
}
