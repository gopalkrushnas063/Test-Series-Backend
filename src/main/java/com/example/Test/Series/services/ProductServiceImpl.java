package com.example.Test.Series.services;

import com.example.Test.Series.entity.Product;
import com.example.Test.Series.exceptions.ProductException;
import com.example.Test.Series.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) throws ProductException {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) throws ProductException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Product not found with id: " + id));
    }

    @Override
    public List<Product> getAllProducts() throws ProductException {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductException("No products found");
        }
        return products;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductException {
        Product existingProduct = getProductById(id);
        existingProduct.setTitle(product.getTitle());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setImage(product.getImage());
        existingProduct.setRating(product.getRating());
        return productRepository.save(existingProduct);
    }

    @Override
    public String deleteProduct(Long id) throws ProductException {
        Product product = getProductById(id);
        productRepository.delete(product);
        return "Product deleted successfully";
    }
}
