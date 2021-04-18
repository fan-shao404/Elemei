package com.example.elemei.view.pojo;

/**
 * Date:2021/4/14
 * Author:fanshaofeng
 */
public class Commodity {

    private int id;
    private String cover;
    private String name;
    private double price;
    private int store_id;

    public Commodity() {

    }

    public Commodity(int id, String cover, String name, double price, int store_id) {
        this.id = id;
        this.cover = cover;
        this.name = name;
        this.price = price;
        this.store_id = store_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", cover='" + cover + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                "store_id=" + store_id +
                '}';
    }
}
