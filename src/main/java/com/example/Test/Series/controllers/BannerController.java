package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.Banner;
import com.example.Test.Series.exceptions.BannerException;
import com.example.Test.Series.services.BannerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerServices bannerService;

    @PostMapping("/register")
    public ResponseEntity<Banner> registerNewBannerHandler(
            @RequestParam("bannerImage") MultipartFile bannerImage,
            @RequestParam("title") String title,
            @RequestParam("subTitle") String subTitle,
            @RequestParam("url") String url) throws BannerException, IOException {
        Banner banner1 = bannerService.registerNewBanner(bannerImage, title, subTitle, url);
        return new ResponseEntity<>(banner1, HttpStatus.ACCEPTED);
    }

    @GetMapping("/all_banners")
    public ResponseEntity<List<Banner>> getAllBannerHandler() throws BannerException {
        List<Banner> banners = bannerService.getAllBanner();
        return new ResponseEntity<>(banners, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Banner> updateBannerByIDHandler(
            @PathVariable("id") Integer id,
            @RequestParam("bannerImage") MultipartFile bannerImage,
            @RequestParam("title") String title,
            @RequestParam("subTitle") String subTitle,
            @RequestParam("url") String url) throws BannerException, IOException {
        Banner banner1 = bannerService.updateBannerByID(id, bannerImage, title, subTitle, url);
        return new ResponseEntity<>(banner1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBannerBYIDHandler(@PathVariable("id") Integer id) throws BannerException {
        String res = bannerService.deleteBannerByID(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Banner> getBannerBYIDHandler(@PathVariable("id") Integer id) throws BannerException {
        Banner res = bannerService.getBannerByID(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
