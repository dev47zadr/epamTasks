package com.epamTasks.task4.services;

import com.epamTasks.task4.models.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class Card {
    private Map<Product, Integer> products = new LinkedHashMap<>();
    private static final Card instance = new Card();

    private Card() {
    }

    ;

    public static Card getInstance() {
        return instance;
    }

    public void add(Product product) {
        int count = products.getOrDefault(product, 0);
        products.put(product, ++count);
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public Map<Product, Integer> checkout() {
        Map<Product, Integer> checkoutProducts = products;
        products = new LinkedHashMap<>();
        return checkoutProducts;
    }
}
