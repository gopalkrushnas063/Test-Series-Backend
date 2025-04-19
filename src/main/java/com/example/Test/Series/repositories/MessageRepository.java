package com.example.Test.Series.repositories;

import com.example.Test.Series.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByTimestampAsc(
            Integer senderId1, Integer receiverId1, Integer senderId2, Integer receiverId2);
}