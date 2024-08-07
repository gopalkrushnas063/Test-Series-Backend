package com.example.Test.Series.services;

import com.example.Test.Series.entity.Submenu;
import com.example.Test.Series.exceptions.MenuException;
import java.util.List;

public interface SubmenuServices {
    List<Submenu> getAllSubmenus() throws MenuException;
    Submenu getSubmenuById(Integer id) throws MenuException;
    Submenu createSubmenu(Submenu submenu) throws MenuException;
    Submenu updateSubmenu(Integer id, Submenu submenu) throws MenuException;
    void deleteSubmenu(Integer id) throws MenuException;
}
