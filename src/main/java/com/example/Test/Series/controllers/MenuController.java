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
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuServices menuService;

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenus() throws MenuException {
        List<Menu> menus = menuService.getAllMenus();
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Integer id) throws MenuException {
        Menu menu = menuService.getMenuById(id);
        return ResponseEntity.ok(menu);
    }

    @PostMapping
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) throws MenuException {
        Menu createdMenu = menuService.createMenu(menu);
        return ResponseEntity.ok(createdMenu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable Integer id, @RequestBody Menu menu) throws MenuException {
        Menu updatedMenu = menuService.updateMenu(id, menu);
        return ResponseEntity.ok(updatedMenu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Integer id) throws MenuException {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }
}
