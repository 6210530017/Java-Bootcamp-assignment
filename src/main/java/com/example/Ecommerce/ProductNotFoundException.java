package com.example.Ecommerce;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String productCategoryName) {
        super(productCategoryName);
    }
}
