package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.Message;
import com.example.Test.Series.repositories.MessageRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        try {
            // Save to database
            Message message = new Message();
            message.setSenderId(chatMessage.getSenderId());
            message.setReceiverId(chatMessage.getReceiverId());
            message.setContent(chatMessage.getContent());
            message.setMessageType(chatMessage.getMessageType());
            message.setTimestamp(LocalDateTime.now());
            message.setIsRead(false);

            Message savedMessage = messageRepository.save(message);

            // Send to receiver's private queue
            messagingTemplate.convertAndSendToUser(
                    chatMessage.getReceiverId().toString(),
                    "/queue/private",
                    savedMessage
            );

            // Also send to the chat room
            String roomId = getRoomId(chatMessage.getSenderId(), chatMessage.getReceiverId());
            messagingTemplate.convertAndSend("/topic/room/" + roomId, savedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getRoomId(Integer user1, Integer user2) {
        return user1 < user2 ? user1 + "_" + user2 : user2 + "_" + user1;
    }
}

@Data
class ChatMessage {
    private Integer senderId;
    private Integer receiverId;
    private String content;
    private String messageType;
}