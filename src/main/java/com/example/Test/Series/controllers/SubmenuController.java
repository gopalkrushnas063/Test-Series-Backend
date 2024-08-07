package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.Submenu;
import com.example.Test.Series.exceptions.MenuException;
import com.example.Test.Series.services.SubmenuServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/submenus")
public class SubmenuController {

    @Autowired
    private SubmenuServices submenuService;

    @GetMapping
    public ResponseEntity<List<Submenu>> getAllSubmenus() throws MenuException {
        List<Submenu> submenus = submenuService.getAllSubmenus();
        return ResponseEntity.ok(submenus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Submenu> getSubmenuById(@PathVariable Integer id) throws MenuException {
        Submenu submenu = submenuService.getSubmenuById(id);
        return ResponseEntity.ok(submenu);
    }

    @PostMapping
    public ResponseEntity<Submenu> createSubmenu(@RequestBody Submenu submenu) throws MenuException {
        Submenu createdSubmenu = submenuService.createSubmenu(submenu);
        return ResponseEntity.ok(createdSubmenu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Submenu> updateSubmenu(@PathVariable Integer id, @RequestBody Submenu submenu) throws MenuException {
        Submenu updatedSubmenu = submenuService.updateSubmenu(id, submenu);
        return ResponseEntity.ok(updatedSubmenu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmenu(@PathVariable Integer id) throws MenuException {
        submenuService.deleteSubmenu(id);
        return ResponseEntity.noContent().build();
    }
}
