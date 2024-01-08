package com.example.Test.Series.services;

import com.example.Test.Series.entity.Banner;
import com.example.Test.Series.exceptions.BannerException;

import java.util.List;

public interface BannerServices {

    public Banner registerNewBanner(Banner banner) throws BannerException;
    public List<Banner> getAllBanner() throws BannerException;
    public Banner updateBannerByID(Integer id, Banner banner) throws BannerException;
    public String  deleteBannerByID(Integer id) throws BannerException;
    public Banner  getBannerByID(Integer id) throws BannerException;
}
