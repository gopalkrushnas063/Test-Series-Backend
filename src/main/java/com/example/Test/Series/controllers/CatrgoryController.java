package com.example.Test.Series.controllers;


import com.example.Test.Series.entity.Category;
import com.example.Test.Series.exceptions.BannerException;
import com.example.Test.Series.exceptions.CategoryException;
import com.example.Test.Series.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CatrgoryController {

    @Autowired
    private CategoryServices categoryServices;

    @PostMapping("/register")
    public ResponseEntity<Category> registerCategory(@RequestBody Category category) throws CategoryException {
        Category category1 = categoryServices.registerNewCategory(category);
        return new ResponseEntity<>(category1, HttpStatus.CREATED);
    }


    @GetMapping("/all_categoriesList")
    public ResponseEntity<List<Category>> getAllCategoriesHandler() throws CategoryException {
        List<Category> categories = categoryServices.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategoryByIDHandler(@PathVariable("id") Integer id,Category category) throws CategoryException{
        Category category1 = categoryServices.updateCategoryByID(id,category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryBYIDHandler(@PathVariable("id") Integer id) throws CategoryException{
        String res = categoryServices.deleteCategoryByID(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryBYIDHandler(@PathVariable("id") Integer id) throws CategoryException{
        Category res = categoryServices.getCategoryByID(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
