package com.example.Test.Series.repositories.Message;

import com.example.Test.Series.entity.Message;
import com.example.Test.Series.exceptions.MessageException;
import com.example.Test.Series.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message sendMessage(Message message) throws MessageException {
        message.setTimestamp(LocalDateTime.now());
        message.setIsRead(false);
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getChatHistory(Integer user1Id, Integer user2Id) throws MessageException {
        return messageRepository.findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByTimestampAsc(
                user1Id, user2Id, user1Id, user2Id);
    }

    @Override
    public Message markAsRead(Integer messageId) throws MessageException {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new MessageException("Message not found"));
        message.setIsRead(true);
        return messageRepository.save(message);
    }
}