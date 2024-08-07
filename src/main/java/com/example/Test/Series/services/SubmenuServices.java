package com.example.Test.Series.services;

import com.example.Test.Series.entity.Submenu;
import com.example.Test.Series.exceptions.NavbarException;

import java.util.List;

public interface SubmenuServices {
    public List<Submenu> getAllSubmenus() throws NavbarException;
    public Submenu getSubmenuById(Integer id) throws  NavbarException;
    public Submenu createSubmenu(Submenu submenu) throws  NavbarException;
    public Submenu updateSubmenu(Integer id, Submenu submenu) throws  NavbarException;
    public void deleteSubmenu(Integer id) throws  NavbarException;
}
