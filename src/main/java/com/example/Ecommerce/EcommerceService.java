package com.example.Ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EcommerceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

//    public String concatData(String message) {
//        return "Object "+message;
//    }

    public String userAddress(String name) {
        Optional<User> user = userRepository.findByName(name);
        return name+"\'s address is "+user.get().getAddress();
    }

    public String searchProduct(String productCategoryName) {
        Optional<Product> product = productRepository.findByCategory(productCategoryName);
        return product.get().getProductName();  //ชั่วคราว
    }
}
