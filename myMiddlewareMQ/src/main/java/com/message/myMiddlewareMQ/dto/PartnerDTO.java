package com.message.myMiddlewareMQ.dto;

import jakarta.validation.constraints.NotNull;

public class PartnerDTO {
    private Long id;
    
    @NotNull(message = "Alias cannot be null")
    private String alias;
    
    @NotNull(message = "Type cannot be null")
    private String type;
    
    @NotNull(message = "Direction cannot be null")
    private String direction;
    
    private String application;
    
    @NotNull(message = "Processed flow type cannot be null")
    private String processedFlowType;
    
    @NotNull(message = "Description cannot be null")
    private String description;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getProcessedFlowType() {
        return processedFlowType;
    }

    public void setProcessedFlowType(String processedFlowType) {
        this.processedFlowType = processedFlowType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
