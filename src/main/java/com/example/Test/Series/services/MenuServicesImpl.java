package com.example.Test.Series.services;

import com.example.Test.Series.entity.Menu;
import com.example.Test.Series.exceptions.MenuException;
import com.example.Test.Series.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServicesImpl implements MenuServices {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuServicesImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> getAllMenus() throws MenuException {
        try {
            return menuRepository.findAll();
        } catch (Exception e) {
            throw new MenuException("Error fetching menus");
        }
    }

    @Override
    public Menu getMenuById(Long id) throws MenuException {
        try {
            Optional<Menu> optionalMenu = menuRepository.findById(id);
            if (optionalMenu.isPresent()) {
                return optionalMenu.get();
            } else {
                throw new MenuException("Menu not found with id: " + id);
            }
        } catch (Exception e) {
            throw new MenuException("Error fetching menu by id: " + id);
        }
    }

    @Override
    public Menu createMenu(Menu menu) throws MenuException {
        try {
            return menuRepository.save(menu);
        } catch (Exception e) {
            throw new MenuException("Error creating menu");
        }
    }

    @Override
    public Menu updateMenu(Long id, Menu menu) throws MenuException {
        try {
            Optional<Menu> optionalMenu = menuRepository.findById(id);
            if (optionalMenu.isPresent()) {
                Menu existingMenu = optionalMenu.get();
                existingMenu.setMenu(menu.getMenu());
                existingMenu.setSubmenus(menu.getSubmenus());
                return menuRepository.save(existingMenu);
            } else {
                throw new MenuException("Menu not found with id: " + id);
            }
        } catch (Exception e) {
            throw new MenuException("Error updating menu");
        }
    }

    @Override
    public void deleteMenu(Long id) throws MenuException {
        try {
            menuRepository.deleteById(id);
        } catch (Exception e) {
            throw new MenuException("Error deleting menu");
        }
    }
}
