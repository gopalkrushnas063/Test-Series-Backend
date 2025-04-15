package com.example.Test.Series.repositories;

import com.example.Test.Series.entity.CourseData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDataRepository extends JpaRepository<CourseData, Integer> {
}