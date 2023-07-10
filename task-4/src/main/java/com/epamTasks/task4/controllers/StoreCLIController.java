package com.epamTasks.task4.controllers;

import com.epamTasks.task4.models.Order;
import com.epamTasks.task4.services.Store;
import com.epamTasks.task4.models.Product;
import com.epamTasks.task4.services.Card;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class StoreCLIController implements Controller {
    private Store store = Store.getInstance();
    private Card card = Card.getInstance();

    public void handleRequest() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printGeneralInfo();
            System.out.print("Enter:");
            String arg = scanner.nextLine();
            if (validateGeneralInfo(arg)) {
                handleGeneralOption(arg.charAt(0));
            } else {
                System.out.println("unknown option!");
            }
        }
    }

    private void printProduct(Product product) {
        System.out.println("id:" + product.getId() + " " + product);
    }

    private boolean validateGeneralInfo(String str) {
        List<Character> availableCases = new ArrayList<>(Arrays.asList('s', 'a', 'c', 'b', 'l', 'f', 'r'));
        if (!Objects.isNull(str) && str.length() == 1) {
            return availableCases.contains(str.charAt(0));
        }
        return false;
    }

    private boolean validateStringDate(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        try {
            LocalDateTime.parse(str, formatter);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private LocalDateTime convertToDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    private void printGeneralInfo() {
        System.out.println("s - show products");
        System.out.println("a - add products");
        System.out.println("c - show card products");
        System.out.println("b - buy products in card");
        System.out.println("l - show latest 5 added product");
        System.out.println("f - find order");
        System.out.println("r - range orders");
    }

    private void handleGeneralOption(char option) {
        switch (option) {
            case 's':
                printProducts();
                break;
            case 'a':
                handleAddProduct();
                break;
            case 'c':
                printCardProducts();
                break;
            case 'b':
                checkout();
                break;
            case 'l':
                printLatestFiveProducts();
                break;
            case 'f':
                handleFindOrder();
                break;
            case 'r':
                handleRangeOrder();
                break;
        }
    }

    private void printProducts() {
        for (Product product : store.getProducts()) {
            printProduct(product);
        }
    }

    private void handleAddProduct() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printProducts();
            System.out.println("Please enter product id to add to card.");
            System.out.print("Enter:");
            try {
                var id = Integer.parseInt(scanner.nextLine());
                Product product = store.getProduct(id);
                card.add(product);
                System.out.println("product added to card");
                printProduct(product);
                break;
            } catch (Exception e) {
                System.out.println("unknown product id!");
            }
        }
    }

    private void printCardProducts() {
        for (Map.Entry<Product, Integer> entry : card.getProducts().entrySet()) {
            Product product = entry.getKey();
            int count = entry.getValue();
            for (int j = 0; j < count; j++) {
                printProduct(product);
            }
        }
    }

    private void checkout() {
        double total = store.addOrder();
        System.out.println("total price is " + total + " thanks for purchase.");
    }

    private void printLatestFiveProducts() {
        int i = 0;
        for (Map.Entry<Product, Integer> entry : store.getLatestProducts().entrySet()) {
            Product product = entry.getKey();
            int count = entry.getValue();
            for (int j = 0; j < count && i++ <= 5; j++) {
                printProduct(product);
            }
        }
    }

    private void handleRangeOrder() {
        Scanner scanner = new Scanner(System.in);
        LocalDateTime from;
        LocalDateTime to;
        while (true) {
            System.out.println("Enter date from with format YYYY.MM.DD hh:mm:ss");
            System.out.print("Enter:");
            String fromDate = scanner.nextLine();
            if (validateStringDate(fromDate)) {
                from = convertToDateTime(fromDate);
                break;
            } else {
                System.out.println("incorrect format date!");
            }
        }
        while (true) {
            System.out.println("Enter date to with format YYYY.MM.DD hh:mm:ss");
            System.out.print("Enter:");
            String fromDate = scanner.nextLine();
            if (validateStringDate(fromDate)) {
                to = convertToDateTime(fromDate);
                break;
            } else {
                System.out.println("incorrect format date!");
            }
        }
        for (Order order : store.getOrders(from, to)) {
            System.out.println(order);
        }
    }

    private void handleFindOrder() {
        Scanner scanner = new Scanner(System.in);
        LocalDateTime date;
        while (true) {
            System.out.println("Enter date from with format YYYY.MM.DD hh:mm:ss");
            System.out.print("Enter:");
            String fromDate = scanner.nextLine();
            if (validateStringDate(fromDate)) {
                date = convertToDateTime(fromDate);
                break;
            } else {
                System.out.println("incorrect format date!");
            }
        }
        System.out.println(store.getOrder(date));
    }
}
