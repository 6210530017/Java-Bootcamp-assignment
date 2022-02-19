package com.example.Ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private List<Product> products;
    private int quantity = 0;
    private int price = 0;

    public Basket() {
        products = new ArrayList<Product>();
    }

    public void addToBasket(Product product, int quantity) {
        if (products == null) {
            products = new ArrayList<Product>();
        }
        products.add(product);
        this.quantity += quantity;
        price += product.getPrice() * quantity;
    }
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
