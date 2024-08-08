package com.example.Test.Series.repositories;

import com.example.Test.Series.entity.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Integer> {
}
