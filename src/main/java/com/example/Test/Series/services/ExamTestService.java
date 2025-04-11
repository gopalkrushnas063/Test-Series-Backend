package com.example.Test.Series.services;

import com.example.Test.Series.entity.ExamTest;
import com.example.Test.Series.exceptions.ExamCardExceptions;

import java.util.List;

public interface ExamTestService {
    ExamTest createExamTest(ExamTest examTest) throws ExamCardExceptions;
    List<ExamTest> getExamTestsByExamCardId(Integer examCardId) throws ExamCardExceptions;
    ExamTest getExamTestById(Integer id) throws ExamCardExceptions;
}