package com.example.Test.Series.services;

import com.example.Test.Series.entity.Navbar;
import com.example.Test.Series.exceptions.NavbarException;
import com.example.Test.Series.repositories.NavbarRepository;
import com.example.Test.Series.services.NavbarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NavbarServicesImpl implements NavbarServices {

    private final NavbarRepository navbarRepository;

    @Autowired
    public NavbarServicesImpl(NavbarRepository navbarRepository) {
        this.navbarRepository = navbarRepository;
    }

    @Override
    public List<Navbar> getAllNavbars() throws NavbarException {
        try {
            return navbarRepository.findAll();
        } catch (Exception e) {
            throw new NavbarException("Error fetching navbars");
        }
    }

    @Override
    public Navbar getNavbarById(Integer id) throws NavbarException {
        try {
            Optional<Navbar> optionalNavbar = navbarRepository.findById(id);
            if (optionalNavbar.isPresent()) {
                return optionalNavbar.get();
            } else {
                throw new NavbarException("Navbar not found with id: " + id);
            }
        } catch (Exception e) {
            throw new NavbarException("Error fetching navbar by id: " + id);
        }
    }

    @Override
    public Navbar createNavbar(Navbar navbar) throws NavbarException {
        try {
            return navbarRepository.save(navbar);
        } catch (Exception e) {
            throw new NavbarException("Error creating navbar");
        }
    }

    @Override
    public Navbar updateNavbar(Integer id, Navbar navbar) throws NavbarException {
        try {
            Optional<Navbar> optionalNavbar = navbarRepository.findById(id);
            if (optionalNavbar.isPresent()) {
                Navbar existingNavbar = optionalNavbar.get();
                existingNavbar.setMenu(navbar.getMenu());
                existingNavbar.setSubmenus(navbar.getSubmenus());
                return navbarRepository.save(existingNavbar);
            } else {
                throw new NavbarException("Navbar not found with id: " + id);
            }
        } catch (Exception e) {
            throw new NavbarException("Error updating navbar");
        }
    }

    @Override
    public void deleteNavbar(Integer id) throws NavbarException {
        try {
            navbarRepository.deleteById(id);
        } catch (Exception e) {
            throw new NavbarException("Error deleting navbar");
        }
    }
}
