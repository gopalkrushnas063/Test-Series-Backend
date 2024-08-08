package com.example.Test.Series.services;

import com.example.Test.Series.entity.FAQ;
import com.example.Test.Series.exceptions.FAQException;
import com.example.Test.Series.repositories.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FAQServicesImpl implements FAQServices {

    @Autowired
    private FAQRepository faqRepository;

    @Override
    public FAQ registerNewFAQ(FAQ faq) throws FAQException {
        return faqRepository.save(faq);
    }

    @Override
    public List<FAQ> getAllFAQs() throws FAQException {
        List<FAQ> faqList = faqRepository.findAll();
        if (faqList.isEmpty()) {
            throw new FAQException("No records found");
        }
        return faqList;
    }

    @Override
    public FAQ updateFAQByID(Integer id, FAQ faq) throws FAQException {
        FAQ existingFAQ = faqRepository.findById(id)
                .orElseThrow(() -> new FAQException("No record found with id: " + id));
        existingFAQ.setQuestion(faq.getQuestion());
        existingFAQ.setAnswer(faq.getAnswer());
        return faqRepository.save(existingFAQ);
    }

    @Override
    public String deleteFAQByID(Integer id) throws FAQException {
        Optional<FAQ> faq = faqRepository.findById(id);
        if (faq.isPresent()) {
            faqRepository.deleteById(id);
            return "The record has been deleted successfully";
        }
        throw new FAQException("No such record found with id: " + id);
    }

    @Override
    public FAQ getFAQByID(Integer id) throws FAQException {
        return faqRepository.findById(id)
                .orElseThrow(() -> new FAQException("Record does not exist with ID: " + id));
    }
}
