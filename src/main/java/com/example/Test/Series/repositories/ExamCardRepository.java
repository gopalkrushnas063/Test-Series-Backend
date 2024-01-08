package com.example.Test.Series.repositories;

import com.example.Test.Series.entity.ExamsCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExamCardRepository extends JpaRepository<ExamsCard,Integer> {
}
