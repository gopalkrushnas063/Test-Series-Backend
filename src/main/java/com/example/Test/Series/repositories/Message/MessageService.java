package com.example.Test.Series.repositories.Message;

import com.example.Test.Series.entity.Message;
import com.example.Test.Series.exceptions.MessageException;
import java.util.List;

public interface MessageService {
    Message sendMessage(Message message) throws MessageException;
    List<Message> getChatHistory(Integer user1Id, Integer user2Id) throws MessageException;
    Message markAsRead(Integer messageId) throws MessageException;
}