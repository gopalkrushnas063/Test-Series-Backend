package com.example.Test.Series.repositories;

import com.example.Test.Series.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BannerRepository extends JpaRepository<Banner,Integer> {
}
