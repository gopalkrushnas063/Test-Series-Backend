// ExamTestController.java
package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.ExamTest;
import com.example.Test.Series.exceptions.ExamCardExceptions;
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
    public ResponseEntity<ExamTest> createExamTest(@RequestBody ExamTest examTest) throws ExamCardExceptions {
        ExamTest createdExamTest = examTestService.createExamTest(examTest);
        return new ResponseEntity<>(createdExamTest, HttpStatus.CREATED);
    }

    @GetMapping("/by-exam-card/{examCardId}")
    public ResponseEntity<List<ExamTest>> getExamTestsByExamCardId(@PathVariable Integer examCardId) throws ExamCardExceptions {
        List<ExamTest> examTests = examTestService.getExamTestsByExamCardId(examCardId);
        return new ResponseEntity<>(examTests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamTest> getExamTestById(@PathVariable Integer id) throws ExamCardExceptions {
        ExamTest examTest = examTestService.getExamTestById(id);
        return new ResponseEntity<>(examTest, HttpStatus.OK);
    }
}