package com.example.Ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EcommerceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public List<Product> getProductById(String productId) {
        int pId = Integer.parseInt(productId);
        List<Product> product = productRepository.findByProductId(pId);
        return product;
    }

//    public String userAddress(String name) {
//        Optional<User> user = userRepository.findByName(name);
//        return name+"\'s address is "+user.get().getAddress();
//    }

    public List<Product> searchProduct(String productCategoryName) {
        List<Product> product = productRepository.findByCategory(productCategoryName);
        return product;
    }
}
