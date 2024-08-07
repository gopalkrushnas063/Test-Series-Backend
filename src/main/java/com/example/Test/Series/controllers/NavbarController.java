package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.Navbar;
import com.example.Test.Series.exceptions.NavbarException;
import com.example.Test.Series.services.NavbarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/navbar")
public class NavbarController {
    @Autowired
    private NavbarServices navbarServices;

    @GetMapping
    public ResponseEntity<List<Navbar>> getAllNavbars() throws NavbarException {
        List<Navbar> navbars = navbarServices.getAllNavbars();
        return ResponseEntity.ok(navbars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Navbar> getNavbarById(@PathVariable Integer id) throws NavbarException {
        Navbar navbar = navbarServices.getNavbarById(id);
        return ResponseEntity.ok(navbar);
    }

    @PostMapping
    public ResponseEntity<Navbar> createNavbar(@RequestBody Navbar navbar) throws NavbarException {
        Navbar createdNavbar = navbarServices.createNavbar(navbar);
        return ResponseEntity.ok(createdNavbar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Navbar> updateNavbar(@PathVariable Integer id, @RequestBody Navbar navbar) throws NavbarException {
        Navbar updatedNavbar = navbarServices.updateNavbar(id, navbar);
        return ResponseEntity.ok(updatedNavbar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNavbar(@PathVariable Integer id) throws NavbarException {
        navbarServices.deleteNavbar(id);
        return ResponseEntity.noContent().build();
    }
}
