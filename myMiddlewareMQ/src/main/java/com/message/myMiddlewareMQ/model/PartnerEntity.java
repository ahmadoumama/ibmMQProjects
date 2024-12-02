package com.message.myMiddlewareMQ.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

@Entity
public class PartnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String alias;
    @NotNull
    private String type; // INBOUND, OUTBOUND
    @NotNull
    private String direction;
    private String application;
    @NotNull
    private String processedFlowType; // MESSAGE, ALERTING, NOTIFICATION
    @NotNull
    private String description;

    public @NotNull String getDescription() {
        return description;
    }

    public @NotNull String getProcessedFlowType() {
        return processedFlowType;
    }

    public String getApplication() {
        return application;
    }

    public @NotNull String getDirection() {
        return direction;
    }

    public @NotNull String getType() {
        return type;
    }

    public @NotNull String getAlias() {
        return alias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAlias(@NotNull String alias) {
        this.alias = alias;
    }

    public void setType(@NotNull String type) {
        this.type = type;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public void setDirection(@NotNull String direction) {
        this.direction = direction;
    }

    public void setProcessedFlowType(@NotNull String processedFlowType) {
        this.processedFlowType = processedFlowType;
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }
}
