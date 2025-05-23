package com.example.Test.Series.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String questionText;

    @ElementCollection
    private List<String> options;

    private Integer correctAnswer;

    @ManyToOne
    @JoinColumn(name = "exam_test_id")
    private ExamTest examTest;
}