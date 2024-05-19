package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(Long productId);
    List<Product> getAllProducts();
    Product createProduct(Product product);
    void updateProduct(Long productId, Product product);
    boolean deleteProduct(Long productId);
}
