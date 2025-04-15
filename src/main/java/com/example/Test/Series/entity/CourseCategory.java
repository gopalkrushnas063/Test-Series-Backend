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
public class CourseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String categoryName; // SSC, Bank etc.
    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<CourseData> data;
}