package com.example.Test.Series.repositories;

import com.example.Test.Series.entity.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Integer> {
    CourseCategory findByCategoryName(String categoryName);
}