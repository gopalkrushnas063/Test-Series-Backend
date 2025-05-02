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
    private String description;
    private String category;
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