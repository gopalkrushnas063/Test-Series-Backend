package com.example.Test.Series.services;

import com.example.Test.Series.entity.Exam;
import com.example.Test.Series.exceptions.ExamException;

import java.util.List;

public interface ExamServices {
    public Exam registerNewExam(Exam exam) throws ExamException;
    public List<Exam> getAllExams() throws ExamException;
    public Exam updateExamByID(Integer id, Exam exam) throws ExamException;
    public String deleteExamByID(Integer id) throws ExamException;
    public Exam getExamByID(Integer id) throws ExamException;
}
