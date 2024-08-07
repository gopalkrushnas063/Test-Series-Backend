package com.example.Test.Series.services;

import com.example.Test.Series.entity.Menu;
import com.example.Test.Series.exceptions.MenuException;

import java.util.List;

public interface MenuServices {
    List<Menu> getAllMenus() throws MenuException;
    Menu getMenuById(Long id) throws MenuException;
    Menu createMenu(Menu menu) throws MenuException;
    Menu updateMenu(Long id, Menu menu) throws MenuException;
    void deleteMenu(Long id) throws MenuException;
}
