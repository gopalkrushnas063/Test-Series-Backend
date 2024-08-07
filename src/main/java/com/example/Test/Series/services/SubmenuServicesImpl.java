package com.example.Test.Series.services;

import com.example.Test.Series.entity.Submenu;
import com.example.Test.Series.exceptions.MenuException;
import com.example.Test.Series.repositories.SubmenuRepository;
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
    public List<Submenu> getAllSubmenus() throws MenuException {
        try {
            return submenuRepository.findAll();
        } catch (Exception e) {
            throw new MenuException("Error fetching submenus");
        }
    }

    @Override
    public Submenu getSubmenuById(Long id) throws MenuException {
        try {
            Optional<Submenu> optionalSubmenu = submenuRepository.findById(id);
            if (optionalSubmenu.isPresent()) {
                return optionalSubmenu.get();
            } else {
                throw new MenuException("Submenu not found with id: " + id);
            }
        } catch (Exception e) {
            throw new MenuException("Error fetching submenu by id: " + id);
        }
    }

    @Override
    public Submenu createSubmenu(Submenu submenu) throws MenuException {
        try {
            return submenuRepository.save(submenu);
        } catch (Exception e) {
            throw new MenuException("Error creating submenu");
        }
    }

    @Override
    public Submenu updateSubmenu(Long id, Submenu submenu) throws MenuException {
        try {
            Optional<Submenu> optionalSubmenu = submenuRepository.findById(id);
            if (optionalSubmenu.isPresent()) {
                Submenu existingSubmenu = optionalSubmenu.get();
                existingSubmenu.setSubmenu(submenu.getSubmenu());
                existingSubmenu.setPath(submenu.getPath());
                existingSubmenu.setMenu(submenu.getMenu()); // Updated from navbar to menu
                return submenuRepository.save(existingSubmenu);
            } else {
                throw new MenuException("Submenu not found with id: " + id);
            }
        } catch (Exception e) {
            throw new MenuException("Error updating submenu");
        }
    }

    @Override
    public void deleteSubmenu(Long id) throws MenuException {
        try {
            submenuRepository.deleteById(id);
        } catch (Exception e) {
            throw new MenuException("Error deleting submenu");
        }
    }
}
