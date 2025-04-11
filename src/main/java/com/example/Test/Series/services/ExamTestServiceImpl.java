// ExamTestServiceImpl.java
package com.example.Test.Series.services.impl;

import com.example.Test.Series.entity.ExamTest;
import com.example.Test.Series.exceptions.ExamCardExceptions;
import com.example.Test.Series.repositories.ExamTestRepository;
import com.example.Test.Series.services.ExamTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamTestServiceImpl implements ExamTestService {

    @Autowired
    private ExamTestRepository examTestRepository;

    @Override
    public ExamTest createExamTest(ExamTest examTest) throws ExamCardExceptions {
        return examTestRepository.save(examTest);
    }

    @Override
    public List<ExamTest> getExamTestsByExamCardId(Integer examCardId) throws ExamCardExceptions {
        List<ExamTest> examTests = examTestRepository.findByExamsCardId(examCardId);
        if(examTests.isEmpty()) {
            throw new ExamCardExceptions("No exam tests found for exam card ID: " + examCardId);
        }
        return examTests;
    }

    @Override
    public ExamTest getExamTestById(Integer id) throws ExamCardExceptions {
        return examTestRepository.findById(id)
                .orElseThrow(() -> new ExamCardExceptions("Exam test not found with ID: " + id));
    }
}