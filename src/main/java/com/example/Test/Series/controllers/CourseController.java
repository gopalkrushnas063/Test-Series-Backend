package com.example.Test.Series.controllers;

import com.example.Test.Series.entity.CourseCategory;
import com.example.Test.Series.entity.CourseData;
import com.example.Test.Series.exceptions.CourseException;
import com.example.Test.Series.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/category")
    public ResponseEntity<CourseCategory> createCategory(@RequestBody CourseCategory categoryRequest) throws CourseException {
        // Create new category without the data first
        CourseCategory category = new CourseCategory();
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setImageUrl(categoryRequest.getImageUrl());

        // Save the category first to get an ID
        CourseCategory savedCategory = courseService.createOrUpdateCategory(category);

        // If there's data, process it
        if (categoryRequest.getData() != null && !categoryRequest.getData().isEmpty()) {
            for (CourseData courseData : categoryRequest.getData()) {
                // Set the category reference for each course data
                courseData.setCategory(savedCategory);
                // Save each course data
                courseService.addCourseToCategory(savedCategory.getCategoryName(), courseData);
            }
        }

        // Fetch the complete category with all data to return
        CourseCategory completeCategory = courseService.getCategoryByName(savedCategory.getCategoryName());
        return new ResponseEntity<>(completeCategory, HttpStatus.CREATED);
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<CourseCategory> getCategory(@PathVariable String name) throws CourseException {
        CourseCategory category = courseService.getCategoryByName(name);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<CourseCategory>> getAllCategories() throws CourseException {
        List<CourseCategory> categories = courseService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) throws CourseException {
        String message = courseService.deleteCategory(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/{categoryName}/data")
    public ResponseEntity<CourseData> addCourseData(@PathVariable String categoryName, @RequestBody CourseData data) throws CourseException {
        CourseData saved = courseService.addCourseToCategory(categoryName, data);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/data/{id}")
    public ResponseEntity<CourseData> updateCourseData(@PathVariable Integer id, @RequestBody CourseData data) throws CourseException {
        CourseData updated = courseService.updateCourseData(id, data);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/data/{id}")
    public ResponseEntity<String> deleteCourseData(@PathVariable Integer id) throws CourseException {
        String message = courseService.deleteCourseData(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{categoryName}/data")
    public ResponseEntity<List<CourseData>> getCourseData(@PathVariable String categoryName) throws CourseException {
        List<CourseData> data = courseService.getAllCoursesInCategory(categoryName);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}