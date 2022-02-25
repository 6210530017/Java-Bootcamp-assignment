package com.example.Ecommerce;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("ทดสอบการค้นหาด้วย category เจอ")
    void findByCategory() {
        // Arrange
        Product p1 = new Product(1,"testProduct1",999,"test","BlaBla");
        Product p2 = new Product(2,"testProduct2",1234,"test2","Wow");
        productRepository.save(p1);
        // Act
        List<Product> result = productRepository.findByCategory("test");
        // Assert
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("ทดสอบการค้นหาด้วย category ไม่เจอ")
    void findByCategory_fail() {
        // Act
        List<Product> result = productRepository.findByCategory("test");
        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("ทดสอบค้นหาสินค้าด้วย productId เจอ")
    void findByProductId() {
        // Arrange
        Product p1 = new Product(1,"testProduct1",999,"test","BlaBla");
        Product p2 = new Product(2,"testProduct2",1234,"test2","Wow");
        productRepository.save(p1);
        // Act
        List<Product> result = productRepository.findByProductId(1);
        // Assert
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("ทดสอบค้นหาสินค้าด้วย productId ไม่เจอ")
    void findByProductId_fail() {
        // Act
        List<Product> result = productRepository.findByProductId(99);
        // Assert
        assertTrue(result.isEmpty());
    }
}