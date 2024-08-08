package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.WhyChooseUS;
import com.example.Test.Series.exceptions.WhyChooseUSException;
import com.example.Test.Series.services.WhyChooseUsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/why_choose_us")
public class WhyChooseUSController {

    @Autowired
    private WhyChooseUsServices whyChooseUsServices;

    // Create a new banner
    @PostMapping("/why_choose_us")
    public ResponseEntity<WhyChooseUS> registerWhyChooseUS(@RequestBody WhyChooseUS banner) {
        try {
            WhyChooseUS savedBanner = whyChooseUsServices.registerNewWhyChooseUs(banner);
            return ResponseEntity.ok(savedBanner);
        } catch (WhyChooseUSException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Get all banners
    @GetMapping("/why_choose_us")
    public ResponseEntity<List<WhyChooseUS>> getAllWhyChooseUS() {
        try {
            List<WhyChooseUS> banners = whyChooseUsServices.getAllWhyChooseUs();
            return ResponseEntity.ok(banners);
        } catch (WhyChooseUSException e) {
            return ResponseEntity.noContent().build();
        }
    }

    // Get a banner by ID
    @GetMapping("/why_choose_us/{id}")
    public ResponseEntity<WhyChooseUS> getWhyChooseUSByID(@PathVariable Integer id) {
        try {
            WhyChooseUS banner = whyChooseUsServices.getWhyChooseUsByID(id);
            return ResponseEntity.ok(banner);
        } catch (WhyChooseUSException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update a banner by ID
    @PutMapping("/why_choose_us/{id}")
    public ResponseEntity<WhyChooseUS> updateWhyChooseUSByID(@PathVariable Integer id, @RequestBody WhyChooseUS data) {
        try {
            WhyChooseUS response = whyChooseUsServices.updateWhyChooseUsByID(id, data);
            return ResponseEntity.ok(response);
        } catch (WhyChooseUSException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Delete a banner by ID
    @DeleteMapping("/why_choose_us/{id}")
    public ResponseEntity<String> deleteWhyChooseUSByID(@PathVariable Integer id) {
        try {
            String response = whyChooseUsServices.deleteWhyChooseUsByID(id);
            return ResponseEntity.ok(response);
        } catch (WhyChooseUSException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
