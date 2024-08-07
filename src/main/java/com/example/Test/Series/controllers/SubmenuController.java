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
@RequestMapping("/submenus") // Changed endpoint to /submenus for clarity
public class SubmenuController {
    private final SubmenuServices submenuServices;

    @Autowired
    public SubmenuController(SubmenuServices submenuServices) {
        this.submenuServices = submenuServices;
    }

    @GetMapping
    public ResponseEntity<List<Submenu>> getAllSubmenus() throws MenuException {
        List<Submenu> submenus = submenuServices.getAllSubmenus();
        return ResponseEntity.ok(submenus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Submenu> getSubmenuById(@PathVariable Long id) throws MenuException {
        Submenu submenu = submenuServices.getSubmenuById(id);
        return ResponseEntity.ok(submenu);
    }

    @PostMapping
    public ResponseEntity<Submenu> createSubmenu(@RequestBody Submenu submenu) throws MenuException {
        Submenu createdSubmenu = submenuServices.createSubmenu(submenu);
        return ResponseEntity.ok(createdSubmenu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Submenu> updateSubmenu(@PathVariable Long id, @RequestBody Submenu submenu) throws MenuException {
        Submenu updatedSubmenu = submenuServices.updateSubmenu(id, submenu);
        return ResponseEntity.ok(updatedSubmenu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmenu(@PathVariable Long id) throws MenuException {
        submenuServices.deleteSubmenu(id);
        return ResponseEntity.noContent().build();
    }
}
