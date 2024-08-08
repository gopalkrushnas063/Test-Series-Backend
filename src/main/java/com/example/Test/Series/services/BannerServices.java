package com.example.Test.Series.services;

import com.example.Test.Series.entity.Banner;
import com.example.Test.Series.exceptions.BannerException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BannerServices {
    Banner registerNewBanner(MultipartFile bannerImage, String title, String subTitle, String url) throws BannerException, IOException;
    List<Banner> getAllBanner() throws BannerException;
    Banner updateBannerByID(Integer id, MultipartFile bannerImage, String title, String subTitle, String url) throws BannerException, IOException;
    String deleteBannerByID(Integer id) throws BannerException;
    Banner getBannerByID(Integer id) throws BannerException;
}
