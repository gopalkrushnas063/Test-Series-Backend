package com.example.Test.Series.repositories;

import com.example.Test.Series.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {
    // Custom query method to find emails by recipient
    List<Email> findByRecipient(String recipient);
}