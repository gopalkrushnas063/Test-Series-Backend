package com.example.Test.Series.repositories;


import com.example.Test.Series.entity.WhyChooseUS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhyChooseUSRepository extends JpaRepository<WhyChooseUS,Integer> {
}
