package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.Exam;
import com.example.Test.Series.exceptions.ExamException;
import com.example.Test.Series.services.ExamServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamServices examServices;

    @PostMapping("/register")
    public ResponseEntity<Exam> registerNewExamHandler(@RequestBody Exam exam) throws ExamException {
        Exam registeredExam = examServices.registerNewExam(exam);
        return new ResponseEntity<>(registeredExam, HttpStatus.ACCEPTED);
    }

    @GetMapping("/all_exams")
    public ResponseEntity<List<Exam>> getAllExamsHandler() throws ExamException {
        List<Exam> exams = examServices.getAllExams();
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExamByIDHandler(@PathVariable("id") Integer id, @RequestBody Exam exam) throws ExamException {
        Exam updatedExam = examServices.updateExamByID(id, exam);
        return new ResponseEntity<>(updatedExam, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExamByIDHandler(@PathVariable("id") Integer id) throws ExamException {
        String response = examServices.deleteExamByID(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamByIDHandler(@PathVariable("id") Integer id) throws ExamException {
        Exam exam = examServices.getExamByID(id);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }
}
