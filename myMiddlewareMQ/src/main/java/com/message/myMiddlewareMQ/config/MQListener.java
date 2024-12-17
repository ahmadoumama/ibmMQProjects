package com.message.myMiddlewareMQ.config;

import com.message.myMiddlewareMQ.dto.MessageDTO;
import com.message.myMiddlewareMQ.exceptions.MessageProcessingException;
import com.message.myMiddlewareMQ.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MQ Listener component that processes incoming messages from IBM MQ.
 * Converts received messages to DTOs and saves them using the message service.
 */
@Component
public class MQListener {
    private static final Logger logger = LoggerFactory.getLogger(MQListener.class);

    @Autowired
    private MessageService messageService;

    @JmsListener(destination = "${ibm.mq.queue}")
    public void receiveMessage(String messagePayload) {
        try {
            logger.info("Received message from MQ: {}", messagePayload);
            
            if (messagePayload == null || messagePayload.trim().isEmpty()) {
                logger.warn("Received empty or null message payload");
                return;
            }

            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setPayload(messagePayload);
            messageDTO.setReceivedAt(LocalDateTime.now());

            MessageDTO savedMessage = messageService.saveMessage(messageDTO);
            logger.info("Successfully processed and saved message with ID: {}", savedMessage.getId());
            
        } catch (MessageProcessingException e) {
            logger.error("Error processing message: {}", messagePayload, e);
            // You might want to implement retry logic or dead letter queue handling here
        } catch (Exception e) {
            logger.error("Unexpected error while processing message: {}", messagePayload, e);
            // Consider implementing a dead letter queue for failed messages
        }
    }
}
