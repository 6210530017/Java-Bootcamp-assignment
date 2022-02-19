package com.example.Ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User login(String username) {
        User user = userRepository.findByName(username);
        return user;
    }

    public Basket addToBasket(String productId, int quantity, Basket basket) {
        int pId = Integer.parseInt(productId);
        List<Product> product = productRepository.findByProductId(pId);
        basket.addToBasket(product.get(0),quantity);
        return basket;
    }

    public List<Product> searchProduct(String productCategoryName) {
        List<Product> product = productRepository.findByCategory(productCategoryName);
        return product;
    }

    public PaymentMethod payment(String method, Card detail) {
        return new PaymentMethod(method,detail);
    }

    public PaymentMethod payment(String method, EcommerceResponse response) {
        Linepaydetail detail = new Linepaydetail();
        detail.setOneTimeKey("123456789012");
        detail.setAmount(100);
        detail.setCurrency("THB");
        detail.setProductName(response.getBasket().getProducts().get(0).toString());
        detail.setOrderId("MyWEB123456789");
        return new PaymentMethod(method, detail);
    }
}
