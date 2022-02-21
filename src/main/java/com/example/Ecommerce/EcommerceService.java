package com.example.Ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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
        if (!product.isEmpty()) {
            return product;
        }
        throw new ProductNotFoundException("Product No.: " + productId);
    }

    public User login(String username) {
        User user = userRepository.findByName(username);
        if (user != null) {
            return user;
        }
        throw new UserNotFoundException(username);
    }

    public Basket addToBasket(String productId, int quantity, Basket basket) {
        int pId = Integer.parseInt(productId);
        List<Product> product = productRepository.findByProductId(pId);
        if (!product.isEmpty()) {
            basket.addToBasket(product.get(0), quantity);
            return basket;
        }
        throw new ProductNotFoundException("Product No.: " + productId);
    }

    public List<Product> searchProduct(String productCategoryName) {
        List<Product> product = productRepository.findByCategory(productCategoryName);
        if (!product.isEmpty()) {
            return product;
        }
        throw new ProductNotFoundException(productCategoryName);
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

    public Purchased purchase(EcommerceResponse response) {
        Random rand = new Random();
        String orderId = String.valueOf(rand.nextInt());
        Purchased purchase = new Purchased(response.getBasket(), orderId, response.getUser());
        return purchase;
    }
}
