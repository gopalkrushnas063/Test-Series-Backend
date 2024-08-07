package com.example.Test.Series.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String menu;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Submenu> submenus;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getMenu() { return menu; }
    public void setMenu(String menu) { this.menu = menu; }
    public List<Submenu> getSubmenus() { return submenus; }
    public void setSubmenus(List<Submenu> submenus) { this.submenus = submenus; }
}
