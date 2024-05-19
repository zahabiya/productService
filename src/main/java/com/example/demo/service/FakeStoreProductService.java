package com.example.demo.service;

import com.example.demo.dto.FakeStoreProductDto;
import com.example.demo.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class);
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        if(fakeStoreProductDtos != null) {
            for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
                products.add(fakeStoreProductDto.toProduct());
            }
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        //fakeStoreProductDto.setCategory("Electronics");
        fakeStoreProductDto.setCategory(product.getCategory().getTitle());

        FakeStoreProductDto response = restTemplate.postForObject(
                "http://fakestoreapi.com/products",
                fakeStoreProductDto,
                FakeStoreProductDto.class
        );
        return response.toProduct();
    }

    @Override
    public void updateProduct(Long productId, Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(productId);
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setCategory(product.getCategory().getTitle());

        restTemplate.put(
                "http://fakestoreapi.com/products/" + productId,
                fakeStoreProductDto
        );
    }

    @Override
    public boolean deleteProduct(Long productId) {
        restTemplate.delete(
                "https://fakestoreapi.com/products/" + productId
        );

        return true;
    }
}
