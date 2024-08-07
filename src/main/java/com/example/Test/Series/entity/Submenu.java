package com.example.Test.Series.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Submenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String submenu;
    private String path;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    @JsonBackReference
    private Menu menu;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getSubmenu() { return submenu; }
    public void setSubmenu(String submenu) { this.submenu = submenu; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public Menu getMenu() { return menu; }
    public void setMenu(Menu menu) { this.menu = menu; }
}
