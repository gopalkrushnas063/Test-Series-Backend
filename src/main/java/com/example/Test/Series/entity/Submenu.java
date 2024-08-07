package com.example.Test.Series.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Submenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Changed to Long

    private String submenu;
    private String path;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu; // Renamed from Navbar to Menu
}
