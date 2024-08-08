package com.example.Test.Series.services;

import com.example.Test.Series.entity.Banner;
import com.example.Test.Series.exceptions.BannerException;
import com.example.Test.Series.repositories.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BannerServiceImpl implements BannerServices {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public Banner registerNewBanner(MultipartFile bannerImage, String title, String subTitle, String url) throws BannerException, IOException {
        Banner banner = new Banner();
        banner.setBannerImage(bannerImage.getBytes());
        banner.setTitle(title);
        banner.setSubTitle(subTitle);
        banner.setUrl(url);
        return bannerRepository.save(banner);
    }

    @Override
    public List<Banner> getAllBanner() throws BannerException {
        List<Banner> bannerList = bannerRepository.findAll();

        if (bannerList.isEmpty()) {
            throw new BannerException("No any record found");
        }
        return bannerList;
    }

    @Override
    public Banner updateBannerByID(Integer id, MultipartFile bannerImage, String title, String subTitle, String url) throws BannerException, IOException {
        Banner existingBanner = bannerRepository.findById(id).orElseThrow(() -> new BannerException("Banner does not exist with ID: " + id));
        existingBanner.setBannerImage(bannerImage.getBytes());
        existingBanner.setTitle(title);
        existingBanner.setSubTitle(subTitle);
        existingBanner.setUrl(url);
        return bannerRepository.save(existingBanner);
    }

    @Override
    public String deleteBannerByID(Integer id) throws BannerException {
        Optional<Banner> existingBanner = bannerRepository.findById(id);

        if (existingBanner.isPresent()) {
            bannerRepository.deleteById(id);
            return "Banner Data Deleted Successfully";
        }
        throw new BannerException("Banner does not exist with Banner ID : " + id);
    }

    @Override
    public Banner getBannerByID(Integer id) throws BannerException {
        Optional<Banner> banner = bannerRepository.findById(id);

        if (banner.isPresent()) {
            return banner.get();
        }
        throw new BannerException("Banner does not exist with Banner ID : " + id);
    }
}
