package com.example.Test.Series.services;

import com.example.Test.Series.entity.ExamsCard;
import com.example.Test.Series.exceptions.ExamCardExceptions;
import com.example.Test.Series.repositories.ExamCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamCardServiceImpl implements ExamRepositoryServices {

    @Autowired
    private ExamCardRepository examCardRepository;

    @Override
    public ExamsCard addExamCard(ExamsCard examsCard) throws ExamCardExceptions {
        return examCardRepository.save(examsCard);
    }

    @Override
    public Map<String, List<ExamsCard>> getAllExamCardsGroupedByCategory() throws ExamCardExceptions {
        List<ExamsCard> allExamCards = examCardRepository.findAll();

        if(allExamCards.isEmpty()) {
            throw new ExamCardExceptions("No any exam card record found");
        }

        // Group exams by category
        Map<String, List<ExamsCard>> examsByCategory = allExamCards.stream()
                .collect(Collectors.groupingBy(ExamsCard::getCategory));

        return examsByCategory;
    }

    @Override
    public ExamsCard updateExamCardByID(Integer id, ExamsCard examsCard) throws ExamCardExceptions {
        Optional<ExamsCard> optionalExamCard = examCardRepository.findById(id);

        if(optionalExamCard.isEmpty()) {
            throw new ExamCardExceptions("Exam Card not found with ID: " + id);
        }

        ExamsCard existingExamCard = optionalExamCard.get();
        existingExamCard.setIcon(examsCard.getIcon());
        existingExamCard.setTitle(examsCard.getTitle());
        existingExamCard.setUrl(examsCard.getUrl());
        existingExamCard.setCategory(examsCard.getCategory());

        return examCardRepository.save(existingExamCard);
    }

    @Override
    public String deleteExamCardByID(Integer id) throws ExamCardExceptions {
        Optional<ExamsCard> examsCard = examCardRepository.findById(id);

        if(examsCard.isPresent()){
            examCardRepository.deleteById(id);
            return "Exam Card Data Deleted Successfully";
        }
        throw new ExamCardExceptions("Exam Card does not exist with ExamCard ID: " + id);
    }
}