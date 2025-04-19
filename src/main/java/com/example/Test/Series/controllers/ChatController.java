package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ChatController {

    @MessageMapping("/chat.send")
    @SendToUser("/queue/reply")
    public Message sendPrivateMessage(@Payload Message message,
                                      Principal principal) {
        // Save to database
        // You can add your message saving logic here
        return message;
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public Message sendToRoom(@DestinationVariable String roomId,
                              @Payload Message message) {
        // Save to database
        return message;
    }
}