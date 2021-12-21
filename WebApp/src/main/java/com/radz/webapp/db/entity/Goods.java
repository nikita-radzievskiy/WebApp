package com.radz.webapp.db.entity;

import java.util.Date;

public class Goods extends Module {

    private String name;
    private String price;
    private Date createdAt;
    private String color;
    private long size;
    private long available;
    private String category;

    public void setCategory(String category) {
        this.category = category;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    private String photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public double setPrice(double price) {
        this.price = String.valueOf(price);
        return price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getAvailable() {
        return available;
    }

    public void setAvailable(long available) {
        this.available = available;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", color='" + color + '\'' +
                ", size=" + size +
                ", available=" + available +
                ", category='" + category + '\'' +
                '}';
    }

    public long setAvailable() {
        this.available = available;
        return available;
    }
}
