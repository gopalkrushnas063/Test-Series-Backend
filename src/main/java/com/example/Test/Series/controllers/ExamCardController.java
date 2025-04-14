package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.ExamsCard;
import com.example.Test.Series.exceptions.ExamCardExceptions;
import com.example.Test.Series.services.ExamRepositoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/examcard")
public class ExamCardController {

    @Autowired
    private ExamRepositoryServices examRepositoryServices;

    @PostMapping("/add_exam_card")
    public ResponseEntity<ExamsCard> addNewExamCardHandler(@RequestBody ExamsCard examsCard) throws ExamCardExceptions {
        ExamsCard examsCard1 = examRepositoryServices.addExamCard(examsCard);
        return new ResponseEntity<>(examsCard1, HttpStatus.ACCEPTED);
    }

    @GetMapping("/all_exam_card")
    public ResponseEntity<Map<String, List<ExamsCard>>> getAllExamCardListHandler() throws ExamCardExceptions {
        Map<String, List<ExamsCard>> examsByCategory = examRepositoryServices.getAllExamCardsGroupedByCategory();
        return new ResponseEntity<>(examsByCategory, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamsCard> updateExamCardByIDHandler(@PathVariable("id") Integer id, @RequestBody ExamsCard examsCard) throws ExamCardExceptions {
        ExamsCard updatedExamCard = examRepositoryServices.updateExamCardByID(id, examsCard);
        return new ResponseEntity<>(updatedExamCard, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExamCardByIDHandler(@PathVariable("id") Integer id) throws ExamCardExceptions {
        String res = examRepositoryServices.deleteExamCardByID(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}