package com.message.myMiddlewareMQ.repository;

import com.message.myMiddlewareMQ.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {}

