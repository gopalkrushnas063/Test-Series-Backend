package com.example.Test.Series.services;

import com.example.Test.Series.entity.Email;
import com.example.Test.Series.exceptions.EmailException;
import com.example.Test.Series.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailServices {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Email sendNewEmail(Email email) throws EmailException {
        try {
            // Send the actual email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email.getRecipient());
            mailMessage.setSubject(email.getSubject());
            mailMessage.setText(email.getMessage());
            mailMessage.setFrom(email.getSender() != null ? email.getSender() : "noreply@example.com");

            mailSender.send(mailMessage);

            // If email sent successfully, update status and save to DB
            email.setStatus("SENT");
            return emailRepository.save(email);
        } catch (Exception e) {
            // If email fails, update status and save to DB
            email.setStatus("FAILED");
            emailRepository.save(email);
            throw new EmailException("Failed to send email: " + e.getMessage());
        }
    }

    @Override
    public List<Email> getAllEmails() throws EmailException {
        List<Email> emails = emailRepository.findAll();
        if(emails.isEmpty()) {
            throw new EmailException("No emails found");
        }
        return emails;
    }

    @Override
    public Email getEmailById(Integer id) throws EmailException {
        Optional<Email> email = emailRepository.findById(id);
        if(email.isPresent()) {
            return email.get();
        }
        throw new EmailException("Email not found with ID: " + id);
    }

    @Override
    public String deleteEmailById(Integer id) throws EmailException {
        Optional<Email> email = emailRepository.findById(id);
        if(email.isPresent()) {
            emailRepository.deleteById(id);
            return "Email deleted successfully";
        }
        throw new EmailException("Email not found with ID: " + id);
    }

    @Override
    public List<Email> getEmailsByRecipient(String recipient) throws EmailException {
        List<Email> emails = emailRepository.findByRecipient(recipient);
        if(emails.isEmpty()) {
            throw new EmailException("No emails found for recipient: " + recipient);
        }
        return emails;
    }
}