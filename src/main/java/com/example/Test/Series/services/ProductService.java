package com.example.Test.Series.services;



import com.example.Test.Series.entity.Product;
import com.example.Test.Series.exceptions.ProductException;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product) throws ProductException;
    Product getProductById(Long id) throws ProductException;
    List<Product> getAllProducts() throws ProductException;
    Product updateProduct(Long id, Product product) throws ProductException;
    String deleteProduct(Long id) throws ProductException;
}
