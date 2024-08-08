package com.example.Test.Series.services;

import com.example.Test.Series.entity.FAQ;
import com.example.Test.Series.exceptions.FAQException;

import java.util.List;

public interface FAQServices {
    public FAQ registerNewFAQ(FAQ faq) throws FAQException;
    public List<FAQ> getAllFAQs() throws FAQException;
    public FAQ updateFAQByID(Integer id, FAQ faq) throws FAQException;
    public String deleteFAQByID(Integer id) throws FAQException;
    public FAQ getFAQByID(Integer id) throws FAQException;
}
