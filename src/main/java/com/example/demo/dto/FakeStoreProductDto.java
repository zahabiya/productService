package com.example.demo.dto;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);
        Category category = new Category();
        category.setTitle(this.category);
        product.setCategory(category);
        product.setDescription(description);
        product.setImageUrl(image);
        return product;
    }
}
