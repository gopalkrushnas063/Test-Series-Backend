package com.example.Test.Series.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer senderId;
    private Integer receiverId;
    private String content;
    private String messageType; // "TEXT", "IMAGE", "VIDEO"
    private String fileUrl;
    private LocalDateTime timestamp;
    private Boolean isRead;
}