package com.epamTasks.task1.entity;

import java.awt.*;

public class Dress extends Product {
    private Color color;
    private Size size;
    public Dress() {
        super(0,0,"No Set");
        this.color = Color.BLACK;
        this.size = Size.M;
    }
    public Dress(long id, int price, String title, Color color, Size size) {
        super(id,price,title);
        this.color = color;
        this.size = size;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Size getSize() {
        return this.size;
    }
    public void setSize(Size size) {
        this.size = size;
    }
    public String toString() {
        return super.toString() + " " + this.getColor() + " " + this.getSize();
    }
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Dress dress = (Dress) obj;
        return this.getId() == dress.getId() &&
                this.getPrice() == dress.getPrice() &&
                this.getTitle().equals(dress.getTitle()) &&
                this.getColor().equals(dress.getColor()) &&
                this.getSize().equals(dress.getSize());
    }
    public int hashCode() {
        return super.hashCode() + this.getColor().hashCode() + this.getSize().hashCode();
    }

    public enum Size{S,M,L,XL,XXL}
}
