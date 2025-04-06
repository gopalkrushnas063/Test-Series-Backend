package com.example.Test.Series.services;

import com.example.Test.Series.entity.Category;
import com.example.Test.Series.exceptions.CategoryException;
import com.example.Test.Series.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryServices{
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Category registerNewCategory(Category category) throws CategoryException {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() throws CategoryException {
        List<Category> categories = categoryRepository.findAll();

        if(categories.isEmpty()){
            throw new CategoryException("Category not found");
        }
        return categories;
    }

    @Override
    public Category updateCategoryByID(Integer id, Category cat) throws CategoryException {
        Category existingCategory = categoryRepository.findById(id).get();
        existingCategory.setCatNames(existingCategory.getCatNames());
        existingCategory.setCatColors(existingCategory.getCatColors());
        existingCategory.setCatIcons(existingCategory.getCatIcons());

        return categoryRepository.save(existingCategory);
    }

    @Override
    public String deleteCategoryByID(Integer id) throws CategoryException {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            return "Category deleted";
        }
        throw new CategoryException("Category does not exist with Category ID : "+id);
    }

    @Override
    public Category getCategoryByID(Integer id) throws CategoryException {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return category.get();
        }
        throw new CategoryException("Category does not exist with Category ID : "+id);
    }
}
