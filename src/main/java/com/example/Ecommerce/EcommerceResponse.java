package com.example.Ecommerce;

import java.util.List;
import java.util.Optional;

public class EcommerceResponse {

//    private String message;
//
//    public EcommerceResponse(String message) {
//        this.message = message;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }

    private List<Product> products;
    private User user;
    private List<Product> basket;
    private PaymentMethod  paymentMethod;

    public EcommerceResponse(List<Product> products, User user, List<Product> basket, PaymentMethod paymentMethod) {
        this.products = products;
        this.user = user;
        this.basket = basket;
        this.paymentMethod = paymentMethod;
    }

    public EcommerceResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getBasket() {
        return basket;
    }

    public void setBasket(List<Product> basket) {
        this.basket = basket;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
