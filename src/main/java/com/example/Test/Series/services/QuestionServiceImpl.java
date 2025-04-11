package com.example.Test.Series.services;

import com.example.Test.Series.entity.Question;
import com.example.Test.Series.exceptions.ExamCardExceptions;
import com.example.Test.Series.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {


    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question createQuestion(Question question) throws ExamCardExceptions {
        try {
            return questionRepository.save(question);
        } catch (Exception e) {
            throw new ExamCardExceptions("Failed to create question: " + e.getMessage());
        }
    }

    @Override
    public Question getQuestionById(Integer id) throws ExamCardExceptions {
        return questionRepository.findById(id)
                .orElseThrow(() -> new ExamCardExceptions("Question not found with ID: " + id));
    }

    @Override
    public List<Question> getAllQuestionsByExamTestId(Integer examTestId) throws ExamCardExceptions {
        List<Question> questions = questionRepository.findByExamId(examTestId);
        if (questions.isEmpty()) {
            throw new ExamCardExceptions("No questions found for ExamTest ID: " + examTestId);
        }
        return questions;
    }

    @Override
    public Question updateQuestion(Integer id, Question questionDetails) throws ExamCardExceptions {
        Question question = getQuestionById(id);

        question.setQuestion(questionDetails.getQuestion());
        question.setOptions(questionDetails.getOptions());
        question.setCorrectAnswer(questionDetails.getCorrectAnswer());

        if (questionDetails.getExamTest() != null) {
            question.setExamTest(questionDetails.getExamTest());
        }

        return questionRepository.save(question);
    }

    @Override
    public String deleteQuestion(Integer id) throws ExamCardExceptions {
        Question question = getQuestionById(id);
        questionRepository.delete(question);
        return "Question with ID " + id + " deleted successfully";
    }

    @Override
    public List<Question> createMultipleQuestions(List<Question> questions) throws ExamCardExceptions {
        try {
            return questionRepository.saveAll(questions);
        } catch (Exception e) {
            throw new ExamCardExceptions("Failed to create multiple questions: " + e.getMessage());
        }
    }
}