package com.example.Test.Series.services;

import com.example.Test.Series.entity.CourseCategory;
import com.example.Test.Series.entity.CourseData;
import com.example.Test.Series.exceptions.CourseException;
import com.example.Test.Series.repositories.CourseCategoryRepository;
import com.example.Test.Series.repositories.CourseDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseCategoryRepository categoryRepository;

    @Autowired
    private CourseDataRepository dataRepository;

    @Override
    public CourseCategory createOrUpdateCategory(CourseCategory category) throws CourseException {
        if (category.getCategoryName() == null || category.getCategoryName().isEmpty()) {
            throw new CourseException("Category name cannot be empty");
        }
        return categoryRepository.save(category);
    }

    @Override
    public CourseCategory getCategoryByName(String categoryName) throws CourseException {
        CourseCategory category = categoryRepository.findByCategoryName(categoryName);
        if (category == null) {
            throw new CourseException("Category not found with name: " + categoryName);
        }
        return category;
    }

    @Override
    public List<CourseCategory> getAllCategories() throws CourseException {
        List<CourseCategory> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new CourseException("No categories found");
        }
        return categories;
    }

    @Override
    public String deleteCategory(Integer id) throws CourseException {
        Optional<CourseCategory> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            return "Category deleted successfully";
        }
        throw new CourseException("Category not found with id: " + id);
    }

    @Override
    public CourseData addCourseToCategory(String categoryName, CourseData courseData) throws CourseException {
        CourseCategory category = getCategoryByName(categoryName);
        courseData.setCategory(category);
        return dataRepository.save(courseData);
    }

    @Override
    public CourseData updateCourseData(Integer courseId, CourseData courseData) throws CourseException {
        Optional<CourseData> existing = dataRepository.findById(courseId);
        if (existing.isPresent()) {
            CourseData toUpdate = existing.get();
            toUpdate.setVideoLink(courseData.getVideoLink());
            toUpdate.setCourseTitle(courseData.getCourseTitle());
            toUpdate.setCreatedBy(courseData.getCreatedBy());
            toUpdate.setDescriptions(courseData.getDescriptions());
            toUpdate.setMaterials(courseData.getMaterials());
            return dataRepository.save(toUpdate);
        }
        throw new CourseException("Course data not found with id: " + courseId);
    }

    @Override
    public String deleteCourseData(Integer courseId) throws CourseException {
        Optional<CourseData> existing = dataRepository.findById(courseId);
        if (existing.isPresent()) {
            dataRepository.delete(existing.get());
            return "Course data deleted successfully";
        }
        throw new CourseException("Course data not found with id: " + courseId);
    }

    @Override
    public List<CourseData> getAllCoursesInCategory(String categoryName) throws CourseException {
        CourseCategory category = getCategoryByName(categoryName);
        return category.getData();
    }
}