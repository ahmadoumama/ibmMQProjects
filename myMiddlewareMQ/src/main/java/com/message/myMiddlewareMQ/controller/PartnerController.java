package com.message.myMiddlewareMQ.controller;

import com.message.myMiddlewareMQ.dto.PartnerDTO;
import com.message.myMiddlewareMQ.service.PartnerService;
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
@RequestMapping("/api/partners")
@Tag(name = "Partners", description = "Partner management APIs")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @PostMapping
    @Operation(summary = "Add new partner", description = "Creates a new partner in the system")
    @ApiResponse(responseCode = "201", description = "Partner successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid partner data")
    public ResponseEntity<PartnerDTO> addPartner(@Valid @RequestBody PartnerDTO partnerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(partnerService.addPartner(partnerDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete partner", description = "Deletes a partner by ID")
    @ApiResponse(responseCode = "204", description = "Partner successfully deleted")
    @ApiResponse(responseCode = "404", description = "Partner not found")
    public ResponseEntity<Void> deletePartner(@PathVariable Long id) {
        if (partnerService.existsByIdPartner(id)) {
            partnerService.deletePartner(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Get all partners", description = "Retrieves a list of all partners in the system")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved partners")
    public ResponseEntity<List<PartnerDTO>> getAllPartners() {
        return ResponseEntity.ok(partnerService.getAllPartner());
    }
}
