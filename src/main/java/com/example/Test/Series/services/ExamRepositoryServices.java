package com.example.Test.Series.services;

import com.example.Test.Series.entity.ExamsCard;
import com.example.Test.Series.exceptions.ExamCardExceptions;

import java.util.List;

public interface ExamRepositoryServices {
    public ExamsCard addExamCard(ExamsCard examsCard) throws ExamCardExceptions;
    public List<ExamsCard> getAllExamCardList() throws ExamCardExceptions;
    public ExamsCard updateExamCardByID(Integer id, ExamsCard examsCard) throws ExamCardExceptions;
    public String  deleteExamCardByID(Integer id) throws ExamCardExceptions;
}
