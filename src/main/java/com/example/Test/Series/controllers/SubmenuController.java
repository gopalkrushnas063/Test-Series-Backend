package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.Submenu;
import com.example.Test.Series.exceptions.NavbarException;
import com.example.Test.Series.services.SubmenuServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/submenu")
public class SubmenuController {
    @Autowired
    private SubmenuServices submenuServices;

    @GetMapping
    public ResponseEntity<List<Submenu>> getAllSubmenus() throws NavbarException {
        List<Submenu> submenus = submenuServices.getAllSubmenus();
        return ResponseEntity.ok(submenus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Submenu> getSubmenuById(@PathVariable Integer id) throws NavbarException {
        Submenu submenu = submenuServices.getSubmenuById(id);
        if (submenu != null) {
            return ResponseEntity.ok(submenu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Submenu> createSubmenu(@RequestBody Submenu submenu) throws NavbarException {
        Submenu createdSubmenu = submenuServices.createSubmenu(submenu);
        return ResponseEntity.ok(createdSubmenu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Submenu> updateSubmenu(@PathVariable Integer id, @RequestBody Submenu submenu) throws NavbarException {
        Submenu updatedSubmenu = submenuServices.updateSubmenu(id, submenu);
        if (updatedSubmenu != null) {
            return ResponseEntity.ok(updatedSubmenu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmenu(@PathVariable Integer id) throws NavbarException {
        submenuServices.deleteSubmenu(id);
        return ResponseEntity.noContent().build();
    }
}
