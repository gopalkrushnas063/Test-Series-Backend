package com.example.Test.Series.services;

import com.example.Test.Series.entity.Navbar;
import com.example.Test.Series.exceptions.NavbarException;

import java.util.List;

public interface NavbarServices {
    public List<Navbar> getAllNavbars() throws NavbarException;
    public Navbar getNavbarById(Integer id) throws NavbarException;
    public Navbar createNavbar(Navbar navbar) throws NavbarException;
    public Navbar updateNavbar(Integer id, Navbar navbar) throws NavbarException;
    public void deleteNavbar(Integer id) throws NavbarException;
}

