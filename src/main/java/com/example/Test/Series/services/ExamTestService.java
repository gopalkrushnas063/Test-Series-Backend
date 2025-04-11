package com.example.Test.Series.services;

import com.example.Test.Series.entity.ExamTest;
import com.example.Test.Series.entity.Question;
import com.example.Test.Series.exceptions.ExamTestException;

import java.util.List;

public interface ExamTestService {
    ExamTest createExamTest(ExamTest examTest) throws ExamTestException;
    List<ExamTest> getExamTestsByCardId(Integer cardId) throws ExamTestException;
    Question addQuestionToExamTest(Integer examTestId, Question question) throws ExamTestException;
    List<Question> getQuestionsByExamTestId(Integer examTestId) throws ExamTestException;
}