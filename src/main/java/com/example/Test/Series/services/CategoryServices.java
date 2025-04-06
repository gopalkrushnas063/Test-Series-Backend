package com.example.Test.Series.services;



import com.example.Test.Series.entity.Category;
import com.example.Test.Series.exceptions.CategoryException;

import java.util.List;

public interface CategoryServices {
    public Category registerNewCategory(Category banner) throws CategoryException;
    public List<Category> getAllCategory() throws CategoryException;
    public Category updateCategoryByID(Integer id, Category banner) throws CategoryException;
    public String  deleteCategoryByID(Integer id) throws CategoryException;
    public Category  getCategoryByID(Integer id) throws CategoryException;
}
