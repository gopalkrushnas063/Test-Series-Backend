package com.example.Test.Series.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double price;
    @Column(length = 1000)
    private String description;
    private String category;
    @Column(length = 500)
    private String image;

    @Embedded
    private Rating rating;
}

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
class Rating {
    private Double rate;
    private Integer count;
}