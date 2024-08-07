package com.example.Test.Series.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Submenu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String submenu;
    private String path;

    @ManyToOne
    private Navbar navbar;
}
