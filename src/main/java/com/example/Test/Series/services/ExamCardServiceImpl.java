package com.example.Test.Series.services;

import com.example.Test.Series.entity.ExamsCard;
import com.example.Test.Series.exceptions.ExamCardExceptions;
import com.example.Test.Series.repositories.ExamCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ExamCardServiceImpl implements ExamRepositoryServices{

    @Autowired
    private ExamCardRepository examCardRepository;


    @Override
    public ExamsCard addExamCard(ExamsCard examsCard) throws ExamCardExceptions {
        return examCardRepository.save(examsCard);
    }

    @Override
    public List<ExamsCard> getAllExamCardList() throws ExamCardExceptions {
        List<ExamsCard> allExamCards = examCardRepository.findAll();

        if(allExamCards.isEmpty()){
            throw new ExamCardExceptions("No any exam card record founds");
        }
        return allExamCards;
    }

    @Override
    public ExamsCard updateExamCardByID(Integer id, ExamsCard examsCard) throws ExamCardExceptions {
        ExamsCard examsCard1 = examCardRepository.findById(id).get();
        examsCard1.setIcon(examsCard1.getIcon());
        examsCard1.setTitle(examsCard.getTitle());
        examsCard1.setUrl(examsCard.getUrl());

        return examCardRepository.save(examsCard);
    }

    @Override
    public String deleteExamCardByID(Integer id) throws ExamCardExceptions {
        Optional<ExamsCard> examsCard = examCardRepository.findById(id);

        if(examsCard.isPresent()){
            examCardRepository.deleteById(id);
            return "Exam Card Data Deleted Successfully";
        }
        throw new ExamCardExceptions("Exam Card does not exist with ExamCard ID : "+id);

    }


}
