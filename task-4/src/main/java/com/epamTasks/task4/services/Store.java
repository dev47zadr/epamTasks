package com.epamTasks.task4.services;

import com.epamTasks.task4.models.Dress;
import com.epamTasks.task4.models.Order;
import com.epamTasks.task4.models.Product;

import java.awt.Color;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.SortedMap;
import java.util.Objects;
import java.util.NoSuchElementException;

public class Store {
    private Product[] products = new Product[]{
            new Dress(1, 11, "Red Dress", Color.RED, Dress.Size.S),
            new Dress(2, 12, "Red Dress", Color.RED, Dress.Size.M),
            new Dress(3, 13, "Red Dress", Color.RED, Dress.Size.L),
            new Dress(4, 12, "Blue Dress", Color.BLUE, Dress.Size.S),
            new Dress(5, 13, "Blue Dress", Color.BLUE, Dress.Size.M),
            new Dress(6, 14, "Blue Dress", Color.BLUE, Dress.Size.L),
            new Dress(7, 9, "Black Dress", Color.BLACK, Dress.Size.S),
            new Dress(8, 10, "Black Dress", Color.BLACK, Dress.Size.M),
            new Dress(9, 11, "Black Dress", Color.BLACK, Dress.Size.L),
            new Dress(10, 11, "Green Dress", Color.GREEN, Dress.Size.S),
            new Dress(11, 12, "Green Dress", Color.GREEN, Dress.Size.M),
            new Dress(12, 13, "Green Dress", Color.GREEN, Dress.Size.L)
    };
    private TreeMap<LocalDateTime, Order> orders = new TreeMap<>();
    private int orderId = 1;
    private Card card = Card.getInstance();
    private static Store instance;

    private Store() {
    }

    public static Store getInstance() {
        if (Objects.isNull(instance)) {
            instance = new Store();
        }
        return instance;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(Arrays.asList(products));
    }

    public double addOrder() {
        double total = 0;
        List<Product> products = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : card.checkout().entrySet()) {
            Product product = entry.getKey();
            int count = entry.getValue();
            for (int i = 0; i < count; i++) {
                products.add(product);
                total += product.getPrice();
            }
        }
        Order order = new Order(orderId++, products);
        orders.put(LocalDateTime.now(), order);
        return total;
    }

    public Map<Product, Integer> getLatestProducts() {
        List<Product> resultProduct = new ArrayList<>();
        Map.Entry<Product, Integer>[] entries = card.getProducts().entrySet().toArray(new Map.Entry[0]);
        for (int i = entries.length - 1; i >= 0; i--) {
            Product product = entries[i].getKey();
            int count = entries[i].getValue();
            for (int j = 0; j < count; j++) {
                resultProduct.add(product);
            }
        }
        for (LocalDateTime date : orders.descendingKeySet()) {
            Order order = orders.get(date);
            Product[] orderProducts = order.getProducts().toArray(new Product[0]);
            for (int i = orderProducts.length - 1; i >= 0; i--) {
                Product product = orderProducts[i];
                if (resultProduct.size() < 5) {
                    resultProduct.add(product);
                }
            }
        }
        Map<Product, Integer> mapProducts = new LinkedHashMap<>();
        for (Product product : resultProduct) {
            int value = mapProducts.getOrDefault(product, 0);
            mapProducts.put(product, ++value);
        }
        return mapProducts;
    }

    public Product getProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new NoSuchElementException();
    }

    public List<Order> getOrders(LocalDateTime from, LocalDateTime to) {
        SortedMap<LocalDateTime, Order> orderMap = orders.subMap(from, to);
        return new ArrayList<>(orderMap.values());
    }

    public Order getOrder(LocalDateTime targetDate) {
        LocalDateTime similarDate = orders.ceilingKey(targetDate);
        if (Objects.isNull(similarDate)) {
            similarDate = orders.floorKey(targetDate);
        }
        if (Objects.isNull(similarDate))
            return null;
        return orders.getOrDefault(similarDate, null);
    }
}