package com.example.Test.Series.entity;



import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String image;
    private String title;
    private String path;

    @ElementCollection
    private List<DataList> dataList;
}