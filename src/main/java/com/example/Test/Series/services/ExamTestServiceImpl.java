package com.example.Test.Series.services;

import com.example.Test.Series.entity.ExamTest;
import com.example.Test.Series.entity.Question;
import com.example.Test.Series.exceptions.ExamTestException;
import com.example.Test.Series.repositories.ExamTestRepository;
import com.example.Test.Series.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamTestServiceImpl implements ExamTestService {

    @Autowired
    private ExamTestRepository examTestRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public ExamTest createExamTest(ExamTest examTest) throws ExamTestException {
        return examTestRepository.save(examTest);
    }

    @Override
    public List<ExamTest> getExamTestsByCardId(Integer cardId) throws ExamTestException {
        List<ExamTest> examTests = examTestRepository.findByExamsCardId(cardId);
        if(examTests.isEmpty()) {
            throw new ExamTestException("No exam tests found for card ID: " + cardId);
        }
        return examTests;
    }

    @Override
    public Question addQuestionToExamTest(Integer examTestId, Question question) throws ExamTestException {
        Optional<ExamTest> examTestOpt = examTestRepository.findById(examTestId);
        if(examTestOpt.isEmpty()) {
            throw new ExamTestException("Exam test not found with ID: " + examTestId);
        }
        question.setExamTest(examTestOpt.get());
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getQuestionsByExamTestId(Integer examTestId) throws ExamTestException {
        List<Question> questions = questionRepository.findByExamTestId(examTestId);
        if(questions.isEmpty()) {
            throw new ExamTestException("No questions found for exam test ID: " + examTestId);
        }
        return questions;
    }
}