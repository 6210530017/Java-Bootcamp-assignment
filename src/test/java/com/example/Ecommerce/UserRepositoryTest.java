package com.example.Ecommerce;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("ค้นหา user=testing เจอ")
    void findByName() {
        // Arrange
        User user = new User();
        user.setName("testing");
        user.setUserId(999);
        user.setAddress("Address testing...");
        userRepository.save(user);
        // Act
        User result = userRepository.findByName("testing");
        // Assert
        assertTrue(result!=null);
    }

    @Test
    @DisplayName("ค้นหา user=testing ไม่เจอ")
    void findByName_fail() {
        // Act
        User result = userRepository.findByName("testing");
        // Assert
        assertTrue(result==null);
    }
}