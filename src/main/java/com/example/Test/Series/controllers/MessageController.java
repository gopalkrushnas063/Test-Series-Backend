package com.example.Test.Series.controllers;

import com.example.Test.Series.repositories.Message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) throws MessageException {
        Message sentMessage = messageService.sendMessage(message);
        return new ResponseEntity<>(sentMessage, HttpStatus.CREATED);
    }

    @GetMapping("/chat/{user1Id}/{user2Id}")
    public ResponseEntity<List<Message>> getChatHistory(
            @PathVariable Integer user1Id, @PathVariable Integer user2Id) throws MessageException {
        List<Message> messages = messageService.getChatHistory(user1Id, user2Id);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PutMapping("/read/{messageId}")
    public ResponseEntity<Message> markAsRead(@PathVariable Integer messageId) throws MessageException {
        Message message = messageService.markAsRead(messageId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}