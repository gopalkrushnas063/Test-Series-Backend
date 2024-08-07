package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.Menu;
import com.example.Test.Series.exceptions.MenuException;
import com.example.Test.Series.services.MenuServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/menus") // Changed endpoint to /menus for consistency
public class MenuController {
    private final MenuServices menuServices;

    @Autowired
    public MenuController(MenuServices menuServices) {
        this.menuServices = menuServices;
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenus() throws MenuException {
        List<Menu> menus = menuServices.getAllMenus();
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Long id) throws MenuException {
        Menu menu = menuServices.getMenuById(id);
        return ResponseEntity.ok(menu);
    }

    @PostMapping
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) throws MenuException {
        Menu createdMenu = menuServices.createMenu(menu);
        return ResponseEntity.ok(createdMenu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable Long id, @RequestBody Menu menu) throws MenuException {
        Menu updatedMenu = menuServices.updateMenu(id, menu);
        return ResponseEntity.ok(updatedMenu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) throws MenuException {
        menuServices.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }
}
