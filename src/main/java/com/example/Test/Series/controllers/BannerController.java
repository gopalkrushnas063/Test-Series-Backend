package com.example.Test.Series.controllers;


import com.example.Test.Series.entity.Banner;
import com.example.Test.Series.exceptions.BannerException;
import com.example.Test.Series.services.BannerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerServices bannerServices;


    @PostMapping("/register")
    public ResponseEntity<Banner> registerNewBannerHandler(@RequestBody Banner banner) throws  BannerException {
        Banner banner1 = bannerServices.registerNewBanner(banner);
        return new ResponseEntity<>(banner1, HttpStatus.ACCEPTED);
    }

    @GetMapping("/all_banners")
    public ResponseEntity<List<Banner>> getAllBannerHandler() throws BannerException{
        List<Banner> banners = bannerServices.getAllBanner();
        return new ResponseEntity<>(banners, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Banner> updateBannerByIDHandler(@PathVariable("id") Integer id,Banner banner) throws BannerException{
        Banner banner1 = bannerServices.updateBannerByID(id,banner);
        return new ResponseEntity<>(banner, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBannerBYIDHandler(@PathVariable("id") Integer id) throws BannerException{
        String res = bannerServices.deleteBannerByID(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Banner> getBannerBYIDHandler(@PathVariable("id") Integer id) throws BannerException{
        Banner res = bannerServices.getBannerByID(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
