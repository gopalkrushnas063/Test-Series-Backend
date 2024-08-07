package com.example.Test.Series.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Navbar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String menu;

    @OneToMany(mappedBy = "navbar")
    private List<Submenu> submenus;
}
