package com.example.elemei.view.pojo;

/**
 * Date:2021/4/14
 * Author:fanshaofeng
 */
public class Commodity {

    private Integer id;
    private String cover;
    private String name;
    private double price;

    public Commodity() {

    }

    public Commodity(Integer id, String cover, String name, double price) {
        this.id = id;
        this.cover = cover;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
                '}';
    }
}
