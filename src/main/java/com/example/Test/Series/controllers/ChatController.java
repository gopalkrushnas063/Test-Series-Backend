package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.Message;
import com.example.Test.Series.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageRepository messageRepository;

    // Track typing status for users
    private final Map<Integer, Map<Integer, Boolean>> typingStatus = new HashMap<>();

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        try {
            // Save message to database
            Message message = new Message();
            message.setSenderId(chatMessage.getSenderId());
            message.setReceiverId(chatMessage.getReceiverId());
            message.setContent(chatMessage.getContent());
            message.setMessageType(chatMessage.getMessageType());
            message.setFileUrl(chatMessage.getFileUrl());
            message.setTimestamp(LocalDateTime.now());
            message.setIsRead(false);

            Message savedMessage = messageRepository.save(message);

            // Prepare response with saved message (includes ID and timestamp from DB)
            Map<String, Object> response = new HashMap<>();
            response.put("id", savedMessage.getId());
            response.put("senderId", savedMessage.getSenderId());
            response.put("receiverId", savedMessage.getReceiverId());
            response.put("content", savedMessage.getContent());
            response.put("messageType", savedMessage.getMessageType());
            response.put("fileUrl", savedMessage.getFileUrl());
            response.put("timestamp", savedMessage.getTimestamp());
            response.put("isRead", savedMessage.getIsRead());

            // Notify sender (confirmation with saved message)
            messagingTemplate.convertAndSendToUser(
                    chatMessage.getSenderId().toString(),
                    "/queue/messages",
                    response
            );

            // Notify receiver
            messagingTemplate.convertAndSendToUser(
                    chatMessage.getReceiverId().toString(),
                    "/queue/messages",
                    response
            );

            // Also broadcast to the chat room (optional)
            String roomId = getRoomId(chatMessage.getSenderId(), chatMessage.getReceiverId());
            messagingTemplate.convertAndSend("/topic/room/" + roomId, response);

        } catch (Exception e) {
            // Send error to sender
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to send message");
            error.put("details", e.getMessage());

            messagingTemplate.convertAndSendToUser(
                    chatMessage.getSenderId().toString(),
                    "/queue/errors",
                    error
            );
        }
    }

    @MessageMapping("/chat.typing")
    public void handleTyping(@Payload TypingNotification notification) {
        // Update typing status
        typingStatus
                .computeIfAbsent(notification.getSenderId(), k -> new HashMap<>())
                .put(notification.getReceiverId(), notification.isTyping());

        // Notify receiver
        messagingTemplate.convertAndSendToUser(
                notification.getReceiverId().toString(),
                "/queue/typing",
                notification
        );
    }

    @MessageMapping("/chat.markAsRead")
    public void markAsRead(@Payload ReadReceipt receipt) {
        messageRepository.findById(receipt.getMessageId()).ifPresent(message -> {
            if (message.getReceiverId().equals(receipt.getReceiverId())) {
                message.setIsRead(true);
                messageRepository.save(message);

                // Notify sender that message was read
                Map<String, Object> response = new HashMap<>();
                response.put("messageId", message.getId());
                response.put("readAt", LocalDateTime.now());

                messagingTemplate.convertAndSendToUser(
                        message.getSenderId().toString(),
                        "/queue/readReceipts",
                        response
                );
            }
        });
    }

    @SubscribeMapping("/user/queue/activity")
    public String handleUserSubscription() {
        return "Connected to chat service";
    }

    private String getRoomId(Integer user1, Integer user2) {
        return user1 < user2 ? user1 + "_" + user2 : user2 + "_" + user1;
    }

    // DTO Classes
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatMessage {
        private Integer senderId;
        private Integer receiverId;
        private String content;
        private String messageType;
        private String fileUrl;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TypingNotification {
        private Integer senderId;
        private Integer receiverId;
        private boolean typing;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReadReceipt {
        private Integer messageId;
        private Integer receiverId;
    }
}