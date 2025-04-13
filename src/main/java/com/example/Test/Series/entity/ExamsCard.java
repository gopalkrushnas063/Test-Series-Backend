package com.example.Test.Series.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
