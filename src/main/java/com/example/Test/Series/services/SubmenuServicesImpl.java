package com.example.Test.Series.services;

import com.example.Test.Series.entity.Submenu;
import com.example.Test.Series.exceptions.NavbarException;
import com.example.Test.Series.repositories.SubmenuRepository;
import com.example.Test.Series.services.SubmenuServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmenuServicesImpl implements SubmenuServices {

    private final SubmenuRepository submenuRepository;

    @Autowired
    public SubmenuServicesImpl(SubmenuRepository submenuRepository) {
        this.submenuRepository = submenuRepository;
    }

    @Override
    public List<Submenu> getAllSubmenus() throws NavbarException {
        try {
            return submenuRepository.findAll();
        } catch (Exception e) {
            throw new NavbarException("Error fetching submenus");
        }
    }

    @Override
    public Submenu getSubmenuById(Integer id) throws NavbarException {
        try {
            Optional<Submenu> optionalSubmenu = submenuRepository.findById(id);
            if (optionalSubmenu.isPresent()) {
                return optionalSubmenu.get();
            } else {
                throw new NavbarException("Submenu not found with id: " + id);
            }
        } catch (Exception e) {
            throw new NavbarException("Error fetching submenu by id: " + id);
        }
    }

    @Override
    public Submenu createSubmenu(Submenu submenu) throws NavbarException {
        try {
            return submenuRepository.save(submenu);
        } catch (Exception e) {
            throw new NavbarException("Error creating submenu");
        }
    }

    @Override
    public Submenu updateSubmenu(Integer id, Submenu submenu) throws NavbarException {
        try {
            Optional<Submenu> optionalSubmenu = submenuRepository.findById(id);
            if (optionalSubmenu.isPresent()) {
                Submenu existingSubmenu = optionalSubmenu.get();
                existingSubmenu.setSubmenu(submenu.getSubmenu());
                existingSubmenu.setPath(submenu.getPath());
                existingSubmenu.setNavbar(submenu.getNavbar());
                return submenuRepository.save(existingSubmenu);
            } else {
                throw new NavbarException("Submenu not found with id: " + id);
            }
        } catch (Exception e) {
            throw new NavbarException("Error updating submenu");
        }
    }

    @Override
    public void deleteSubmenu(Integer id) throws NavbarException {
        try {
            submenuRepository.deleteById(id);
        } catch (Exception e) {
            throw new NavbarException("Error deleting submenu");
        }
    }
}
