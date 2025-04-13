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


    @Override
    public ExamTest updateExamTest(Integer examTestId, ExamTest examTest) throws ExamTestException {
        Optional<ExamTest> existingExamTestOpt = examTestRepository.findById(examTestId);
        if(existingExamTestOpt.isEmpty()) {
            throw new ExamTestException("Exam test not found with ID: " + examTestId);
        }

        ExamTest existingExamTest = existingExamTestOpt.get();
        existingExamTest.setTitle(examTest.getTitle());
        return examTestRepository.save(existingExamTest);
    }

    @Override
    public String deleteExamTest(Integer examTestId) throws ExamTestException {
        Optional<ExamTest> examTestOpt = examTestRepository.findById(examTestId);
        if(examTestOpt.isEmpty()) {
            throw new ExamTestException("Exam test not found with ID: " + examTestId);
        }

        // First delete all questions associated with this exam test
        questionRepository.deleteById(examTestId);

        // Then delete the exam test
        examTestRepository.deleteById(examTestId);

        return "Exam test with ID " + examTestId + " and all its questions have been deleted successfully";
    }

    @Override
    public Question updateQuestion(Integer questionId, Question question) throws ExamTestException {
        Optional<Question> existingQuestionOpt = questionRepository.findById(questionId);
        if(existingQuestionOpt.isEmpty()) {
            throw new ExamTestException("Question not found with ID: " + questionId);
        }

        Question existingQuestion = existingQuestionOpt.get();
        existingQuestion.setQuestionText(question.getQuestionText());
        existingQuestion.setOptions(question.getOptions());
        existingQuestion.setCorrectAnswer(question.getCorrectAnswer());
        // Update other fields as needed

        return questionRepository.save(existingQuestion);
    }

    @Override
    public String deleteQuestion(Integer questionId) throws ExamTestException {
        Optional<Question> questionOpt = questionRepository.findById(questionId);
        if(questionOpt.isEmpty()) {
            throw new ExamTestException("Question not found with ID: " + questionId);
        }

        questionRepository.deleteById(questionId);
        return "Question with ID " + questionId + " has been deleted successfully";
    }
}