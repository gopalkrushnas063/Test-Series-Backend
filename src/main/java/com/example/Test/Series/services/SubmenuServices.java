package com.example.Test.Series.services;

import com.example.Test.Series.entity.Submenu;
import com.example.Test.Series.exceptions.MenuException;

import java.util.List;

public interface SubmenuServices {
    List<Submenu> getAllSubmenus() throws MenuException;
    Submenu getSubmenuById(Long id) throws MenuException;
    Submenu createSubmenu(Submenu submenu) throws MenuException;
    Submenu updateSubmenu(Long id, Submenu submenu) throws MenuException;
    void deleteSubmenu(Long id) throws MenuException;
}
