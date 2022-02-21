package com.example.Ecommerce;

import java.util.ArrayList;
import java.util.List;

public class EcommerceResponse {

    private List<Product> products;
    private User user;
    private Basket basket = new Basket();;
    private PaymentMethod  paymentMethod;
    private Purchased purchased;
    private String message;

    public EcommerceResponse() {
        products = new ArrayList<Product>();
        user = null;
        paymentMethod = null;
        purchased = null;
        message = null;
    }

    public EcommerceResponse(String message) {
        products = new ArrayList<Product>();
        user = null;
        paymentMethod = null;
        purchased = null;
        this.message = message;
    }

    public EcommerceResponse(List<Product> products, User user, Basket basket, PaymentMethod paymentMethod) {
        this.products = products;
        this.user = user;
        this.basket = basket;
        this.paymentMethod = paymentMethod;
        this.purchased = null;
        this.message = null;
    }

    public EcommerceResponse(List<Product> products) {
        this.products = products;
    }

    public EcommerceResponse(User user) {
        this.user = user;
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

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Purchased getPurchased() {
        return purchased;
    }

    public void setPurchased(Purchased purchased) {
        this.purchased = purchased;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
