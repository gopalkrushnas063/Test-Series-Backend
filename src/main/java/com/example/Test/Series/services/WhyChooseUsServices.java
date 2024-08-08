package com.example.Test.Series.services;
import com.example.Test.Series.entity.WhyChooseUS;
import com.example.Test.Series.exceptions.WhyChooseUSException;
import java.util.List;

public interface WhyChooseUsServices {

    public WhyChooseUS registerNewWhyChooseUs(WhyChooseUS data) throws WhyChooseUSException;
    public List<WhyChooseUS> getAllWhyChooseUs() throws WhyChooseUSException;
    public WhyChooseUS updateWhyChooseUsByID(Integer id, WhyChooseUS data) throws WhyChooseUSException;
    public String deleteWhyChooseUsByID(Integer id) throws WhyChooseUSException;
    public WhyChooseUS getWhyChooseUsByID(Integer id) throws WhyChooseUSException;
}
