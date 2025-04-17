package com.example.Test.Series.services;

import com.example.Test.Series.entity.Email;
import com.example.Test.Series.exceptions.EmailException;

import java.util.List;

public interface EmailServices {
    public Email sendNewEmail(Email email) throws EmailException;
    public List<Email> getAllEmails() throws EmailException;
    public Email getEmailById(Integer id) throws EmailException;
    public String deleteEmailById(Integer id) throws EmailException;
    public List<Email> getEmailsByRecipient(String recipient) throws EmailException;
}