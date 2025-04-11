// ExamTest.java
package com.example.Test.Series.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String image;
    private String title;
    private String subTitle;

    @ManyToOne
    @JoinColumn(name = "exam_card_id", referencedColumnName = "id")
    private ExamsCard examsCard;

    @OneToMany(mappedBy = "examTest", cascade = CascadeType.ALL)
    private List<Question> questions;
}