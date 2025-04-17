package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.Email;
import com.example.Test.Series.exceptions.EmailException;
import com.example.Test.Series.services.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailServices emailServices;

    @PostMapping("/send")
    public ResponseEntity<Email> sendEmailHandler(@RequestBody Email email) throws EmailException {
        Email sentEmail = emailServices.sendNewEmail(email);
        return new ResponseEntity<>(sentEmail, HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Email>> getAllEmailsHandler() throws EmailException {
        List<Email> emails = emailServices.getAllEmails();
        return new ResponseEntity<>(emails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Email> getEmailByIdHandler(@PathVariable("id") Integer id) throws EmailException {
        Email email = emailServices.getEmailById(id);
        return new ResponseEntity<>(email, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmailByIdHandler(@PathVariable("id") Integer id) throws EmailException {
        String result = emailServices.deleteEmailById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/recipient/{email}")
    public ResponseEntity<List<Email>> getEmailsByRecipientHandler(@PathVariable("email") String recipient) throws EmailException {
        List<Email> emails = emailServices.getEmailsByRecipient(recipient);
        return new ResponseEntity<>(emails, HttpStatus.OK);
    }
}