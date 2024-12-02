package com.message.myMiddlewareMQ.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String payload;
    private LocalDateTime receivedAt;

    public Long getId() {
        return id;
    }

    public String getPayload() {
        return payload;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }
}