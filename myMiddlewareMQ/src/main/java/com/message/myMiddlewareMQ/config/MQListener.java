package com.message.myMiddlewareMQ.config;

import com.message.myMiddlewareMQ.model.MessageEntity;
import com.message.myMiddlewareMQ.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MQListener {

    @Autowired
    private MessageService messageService;

   @JmsListener(destination = "${ibm.mq.queue}")
    public void receiveMessage(String messagePayload) {
        MessageEntity message = new MessageEntity();
        message.setPayload(messagePayload);
        message.setReceivedAt(LocalDateTime.now());
        messageService.saveMessage(message);
    }
}

