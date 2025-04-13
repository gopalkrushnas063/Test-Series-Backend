package com.example.Test.Series.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ExamsCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String icon;
    private String title;
    private String url;

    @OneToMany(mappedBy = "examsCard", cascade = CascadeType.ALL)
    private List<ExamTest> examTests;
}
