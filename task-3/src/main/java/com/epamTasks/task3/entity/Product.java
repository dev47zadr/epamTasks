package com.epamTasks.task3.entity;

public abstract class Product extends Entity {
    protected int price;
    protected String title;

    protected Product() {
        super(0);
        this.price = 0;
        this.title = "No Set";
    }

    protected Product(long id, int price, String title) {
        super(id);
        this.price = price;
        this.title = title;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return super.toString() + " " + Integer.toString(this.price) + " " + this.title;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Product product = (Product) obj;
        return this.getId() == product.getId() &&
                this.getPrice() == product.getPrice() &&
                this.getTitle().equals(product.getTitle());
    }

    public int hashCode() {
        String defaultStr = "abcd";
        String str = this.toString();
        int length = str.length();
        int sumOfChars = 0;
        for (int i = 0; i < 4; i++) {
            sumOfChars += i < length ? str.charAt(i) : defaultStr.charAt(i);
        }
        return length + sumOfChars;
    }
}
