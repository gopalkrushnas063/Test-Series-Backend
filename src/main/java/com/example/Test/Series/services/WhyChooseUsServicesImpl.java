package com.example.Test.Series.services;

import com.example.Test.Series.entity.WhyChooseUS;
import com.example.Test.Series.exceptions.BannerException;
import com.example.Test.Series.exceptions.WhyChooseUSException;
import com.example.Test.Series.repositories.WhyChooseUSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WhyChooseUsServicesImpl implements WhyChooseUsServices{

    @Autowired
    private WhyChooseUSRepository whyChooseUSRepository;


    @Override
    public WhyChooseUS registerNewWhyChooseUs(WhyChooseUS banner) throws WhyChooseUSException {
        return whyChooseUSRepository.save(banner);
    }

    @Override
    public List<WhyChooseUS> getAllWhyChooseUs() throws WhyChooseUSException {
        List<WhyChooseUS> whyChooseUSList = whyChooseUSRepository.findAll();
        if (whyChooseUSList.isEmpty()){
            throw new WhyChooseUSException("No any records found");
        }
        return whyChooseUSList;
    }

    @Override
    public WhyChooseUS updateWhyChooseUsByID(Integer id, WhyChooseUS data) throws WhyChooseUSException {
        WhyChooseUS whyChooseUS = whyChooseUSRepository.findById(id).get();
        whyChooseUS.setTitle(data.getTitle());
        whyChooseUS.setImage(data.getImage());

        return whyChooseUSRepository.save(whyChooseUS);
    }

    @Override
    public String deleteWhyChooseUsByID(Integer id) throws WhyChooseUSException {
        Optional<WhyChooseUS> whyChooseUS = whyChooseUSRepository.findById(id);
        if (whyChooseUS.isPresent()){
            whyChooseUSRepository.deleteById(id);
            return "The record has been deleted successfully";
        }
        throw new WhyChooseUSException("No such record found with id: " + id);
    }

    @Override
    public WhyChooseUS getWhyChooseUsByID(Integer id) throws WhyChooseUSException {
        Optional<WhyChooseUS> whyChooseUS = whyChooseUSRepository.findById(id);
        if (whyChooseUS.isPresent()){
            return whyChooseUS.get();
        }
        throw new WhyChooseUSException("Record does not exist with ID : "+id);

    }
}
