package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.Message;
import com.example.Test.Series.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageRepository messageRepo;

    public ChatController(SimpMessagingTemplate messagingTemplate, MessageRepository messageRepo) {
        this.messagingTemplate = messagingTemplate;
        this.messageRepo = messageRepo;
    }

    // Handle sending messages
    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessageDto messageDto) {
        // Save to database
        Message message = new Message();
        message.setSenderId(messageDto.getSenderId());
        message.setReceiverId(messageDto.getReceiverId());
        message.setContent(messageDto.getContent());
        message.setMessageType(messageDto.getMessageType());
        message.setTimestamp(LocalDateTime.now());
        message.setIsRead(false);

        Message savedMessage = messageRepo.save(message);

        // Send INSTANTLY to receiver (without waiting for DB save)
        messagingTemplate.convertAndSendToUser(
                messageDto.getReceiverId().toString(),
                "/queue/messages",
                savedMessage
        );

        // Also send back to sender (for confirmation)
        messagingTemplate.convertAndSendToUser(
                messageDto.getSenderId().toString(),
                "/queue/messages",
                savedMessage
        );
    }

    // Handle typing status
    @MessageMapping("/chat.typing")
    public void handleTyping(@Payload TypingDto typingDto) {
        // Send typing status INSTANTLY to receiver
        messagingTemplate.convertAndSendToUser(
                typingDto.getReceiverId().toString(),
                "/queue/typing",
                typingDto
        );
    }
}

// DTO Classes
@Data
@AllArgsConstructor
@NoArgsConstructor
class ChatMessageDto {
    private Integer senderId;
    private Integer receiverId;
    private String content;
    private String messageType;
}

@Data @AllArgsConstructor @NoArgsConstructor
class TypingDto {
    private Integer senderId;
    private Integer receiverId;
    private boolean isTyping;
}