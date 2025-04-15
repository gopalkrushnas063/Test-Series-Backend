package com.example.Test.Series.services;

import com.example.Test.Series.entity.CourseCategory;
import com.example.Test.Series.entity.CourseData;
import com.example.Test.Series.exceptions.CourseException;

import java.util.List;

public interface CourseService {
    CourseCategory createOrUpdateCategory(CourseCategory category) throws CourseException;
    CourseCategory getCategoryByName(String categoryName) throws CourseException;
    List<CourseCategory> getAllCategories() throws CourseException;
    String deleteCategory(Integer id) throws CourseException;

    CourseData addCourseToCategory(String categoryName, CourseData courseData) throws CourseException;
    CourseData updateCourseData(Integer courseId, CourseData courseData) throws CourseException;
    String deleteCourseData(Integer courseId) throws CourseException;
    List<CourseData> getAllCoursesInCategory(String categoryName) throws CourseException;
}