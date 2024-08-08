package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.FAQ;
import com.example.Test.Series.exceptions.FAQException;
import com.example.Test.Series.services.FAQServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/faq")
public class FAQController {

    @Autowired
    private FAQServices faqServices;

    @PostMapping
    public ResponseEntity<FAQ> registerFAQ(@RequestBody FAQ faq) {
        try {
            FAQ savedFAQ = faqServices.registerNewFAQ(faq);
            return ResponseEntity.ok(savedFAQ);
        } catch (FAQException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<FAQ>> getAllFAQs() {
        try {
            List<FAQ> faqs = faqServices.getAllFAQs();
            return ResponseEntity.ok(faqs);
        } catch (FAQException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FAQ> getFAQByID(@PathVariable Integer id) {
        try {
            FAQ faq = faqServices.getFAQByID(id);
            return ResponseEntity.ok(faq);
        } catch (FAQException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FAQ> updateFAQByID(@PathVariable Integer id, @RequestBody FAQ faq) {
        try {
            FAQ updatedFAQ = faqServices.updateFAQByID(id, faq);
            return ResponseEntity.ok(updatedFAQ);
        } catch (FAQException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFAQByID(@PathVariable Integer id) {
        try {
            String response = faqServices.deleteFAQByID(id);
            return ResponseEntity.ok(response);
        } catch (FAQException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
