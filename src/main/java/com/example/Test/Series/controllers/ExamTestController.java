package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.ExamTest;
import com.example.Test.Series.entity.Question;
import com.example.Test.Series.exceptions.ExamTestException;
import com.example.Test.Series.services.ExamTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/examtest")
public class ExamTestController {

    @Autowired
    private ExamTestService examTestService;

    @PostMapping("/create")
    public ResponseEntity<ExamTest> createExamTest(@RequestBody ExamTest examTest) throws ExamTestException {
        ExamTest createdExamTest = examTestService.createExamTest(examTest);
        return new ResponseEntity<>(createdExamTest, HttpStatus.CREATED);
    }

    @GetMapping("/bycard/{cardId}")
    public ResponseEntity<List<ExamTest>> getExamTestsByCardId(@PathVariable Integer cardId) throws ExamTestException {
        List<ExamTest> examTests = examTestService.getExamTestsByCardId(cardId);
        return new ResponseEntity<>(examTests, HttpStatus.OK);
    }

    @PostMapping("/{examTestId}/addquestion")
    public ResponseEntity<Question> addQuestionToExamTest(
            @PathVariable Integer examTestId,
            @RequestBody Question question) throws ExamTestException {
        Question addedQuestion = examTestService.addQuestionToExamTest(examTestId, question);
        return new ResponseEntity<>(addedQuestion, HttpStatus.CREATED);
    }

    @GetMapping("/{examTestId}/questions")
    public ResponseEntity<List<Question>> getQuestionsByExamTestId(
            @PathVariable Integer examTestId) throws ExamTestException {
        List<Question> questions = examTestService.getQuestionsByExamTestId(examTestId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PutMapping("/update/{examTestId}")
    public ResponseEntity<ExamTest> updateExamTest(
            @PathVariable Integer examTestId,
            @RequestBody ExamTest examTest) throws ExamTestException {
        ExamTest updatedExamTest = examTestService.updateExamTest(examTestId, examTest);
        return new ResponseEntity<>(updatedExamTest, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{examTestId}")
    public ResponseEntity<String> deleteExamTest(
            @PathVariable Integer examTestId) throws ExamTestException {
        String message = examTestService.deleteExamTest(examTestId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/questions/update/{questionId}")
    public ResponseEntity<Question> updateQuestion(
            @PathVariable Integer questionId,
            @RequestBody Question question) throws ExamTestException {
        Question updatedQuestion = examTestService.updateQuestion(questionId, question);
        return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
    }

    @DeleteMapping("/questions/delete/{questionId}")
    public ResponseEntity<String> deleteQuestion(
            @PathVariable Integer questionId) throws ExamTestException {
        String message = examTestService.deleteQuestion(questionId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}