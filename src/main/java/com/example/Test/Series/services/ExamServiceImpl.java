package com.example.Test.Series.services;

import com.example.Test.Series.entity.Exam;
import com.example.Test.Series.exceptions.ExamException;
import com.example.Test.Series.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamServices {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Exam registerNewExam(Exam exam) throws ExamException {
        return examRepository.save(exam);
    }

    @Override
    public List<Exam> getAllExams() throws ExamException {
        List<Exam> examList = examRepository.findAll();
        if (examList.isEmpty()) {
            throw new ExamException("No records found");
        }
        return examList;
    }

    @Override
    public Exam updateExamByID(Integer id, Exam exam) throws ExamException {
        Exam existingExam = examRepository.findById(id).orElseThrow(() -> new ExamException("Exam not found"));
        existingExam.setImage(exam.getImage());
        existingExam.setTitle(exam.getTitle());
        existingExam.setPath(exam.getPath());
        existingExam.setDataList(exam.getDataList());
        return examRepository.save(existingExam);
    }

    @Override
    public String deleteExamByID(Integer id) throws ExamException {
        Optional<Exam> existingExam = examRepository.findById(id);
        if (existingExam.isPresent()) {
            examRepository.deleteById(id);
            return "Exam deleted successfully";
        }
        throw new ExamException("Exam does not exist with ID: " + id);
    }

    @Override
    public Exam getExamByID(Integer id) throws ExamException {
        Optional<Exam> exam = examRepository.findById(id);
        return exam.orElseThrow(() -> new ExamException("Exam does not exist with ID: " + id));
    }
}
