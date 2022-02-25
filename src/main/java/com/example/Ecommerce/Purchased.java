package com.example.Ecommerce;

import java.util.List;

public class Purchased {
    private Basket products;
    private String orderId;
    private User buyer;

    public Purchased(Basket products, String orderId, User buyer) {
        this.products = products;
        this.orderId = orderId;
        this.buyer = buyer;
    }

    public Basket getProducts() {
        return products;
    }

    public void setProducts(Basket products) {
        this.products = products;
    }

    public String getOrder() {
        return orderId;
    }

    public void setOrder(String orderId) {
        this.orderId = orderId;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return "Purchased{" +
                "products=" + products +
                ", orderId='" + orderId + '\'' +
                ", buyer=" + buyer +
                '}';
    }
}
