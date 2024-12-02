package com.message.myMiddlewareMQ.controller;

import com.message.myMiddlewareMQ.model.PartnerEntity;
import com.message.myMiddlewareMQ.repository.PartnerRepository;
import com.message.myMiddlewareMQ.service.PartnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @PostMapping
    public ResponseEntity<PartnerEntity> addPartner(@Valid @RequestBody PartnerEntity partner) {
        PartnerEntity savedPartner = partnerService.addPartner(partner);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPartner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartner(@PathVariable Long id) {
        if (partnerService.existsByIdPartner(id)) {
            partnerService.deletePartner(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<PartnerEntity>> getAllUsers() {
        return ResponseEntity.ok(partnerService.getAllPartner());
    }
}

