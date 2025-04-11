package com.example.Test.Series.repositories;

import com.example.Test.Series.entity.ExamTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamTestRepository extends JpaRepository<ExamTest, Integer> {
    List<ExamTest> findByExamsCardId(Integer examCardId);
}