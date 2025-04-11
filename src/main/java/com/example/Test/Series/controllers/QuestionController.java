package com.example.Test.Series.controllers;
import com.example.Test.Series.entity.Question;
import com.example.Test.Series.exceptions.ExamCardExceptions;
import com.example.Test.Series.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        try {
            Question createdQuestion = questionService.createQuestion(question);
            return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
        } catch (ExamCardExceptions e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Question>> createMultipleQuestions(@RequestBody List<Question> questions) {
        try {
            List<Question> createdQuestions = questionService.createMultipleQuestions(questions);
            return new ResponseEntity<>(createdQuestions, HttpStatus.CREATED);
        } catch (ExamCardExceptions e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Integer id) {
        try {
            Question question = questionService.getQuestionById(id);
            return new ResponseEntity<>(question, HttpStatus.OK);
        } catch (ExamCardExceptions e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/exam-test/{examTestId}")
    public ResponseEntity<List<Question>> getQuestionsByExamTestId(@PathVariable Integer examTestId) {
        try {
            List<Question> questions = questionService.getAllQuestionsByExamTestId(examTestId);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (ExamCardExceptions e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(
            @PathVariable Integer id,
            @RequestBody Question questionDetails) {
        try {
            Question updatedQuestion = questionService.updateQuestion(id, questionDetails);
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        } catch (ExamCardExceptions e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        try {
            String message = questionService.deleteQuestion(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (ExamCardExceptions e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}