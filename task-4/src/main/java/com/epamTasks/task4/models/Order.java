package com.epamTasks.task4.models;

import java.util.List;

public class Order extends Entity {
    private double total = 0;
    private List<Product> products;

    public Order(int id, List<Product> products) {
        super(id);
        this.products = products;
        calculateTotal();
    }

    public void calculateTotal() {
        for (Product product : products) {
            total += product.getPrice();
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public String toString() {
        return "ID: " + getId() + getProducts().stream().toString();
    }
}
