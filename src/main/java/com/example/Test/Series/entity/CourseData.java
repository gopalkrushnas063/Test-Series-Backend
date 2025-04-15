package com.example.Test.Series.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CourseData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String videoLink;
    private String courseTitle;
    private String createdBy;
    private String descriptions;

    @ElementCollection
    private List<String> materials;

    @ManyToOne
    private CourseCategory category;
}