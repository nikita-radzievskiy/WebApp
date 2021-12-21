package com.radz.webapp.db.entity;

import java.util.HashMap;
import java.util.Map;


public class Cart {
    private String id;
    private String name;
    private int price;

    public void setBufferGoods(Map<String, Goods> bufferGoods) {
        this.bufferGoods = bufferGoods;
    }

    private Map<String, Goods> bufferGoods;


    public Cart() {
        bufferGoods = new HashMap<>();
    }

    public Map<String, Goods> getBufferGoods() {
        return bufferGoods;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}

