package com.example.Test.Series.services;

import com.example.Test.Series.entity.Question;
import com.example.Test.Series.exceptions.ExamCardExceptions;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question) throws ExamCardExceptions;
    Question getQuestionById(Integer id) throws ExamCardExceptions;
    List<Question> getAllQuestionsByExamTestId(Integer examTestId) throws ExamCardExceptions;
    Question updateQuestion(Integer id, Question question) throws ExamCardExceptions;
    String deleteQuestion(Integer id) throws ExamCardExceptions;
    List<Question> createMultipleQuestions(List<Question> questions) throws ExamCardExceptions;
}