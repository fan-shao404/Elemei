package com.example.elemei.view.pojo;

import java.util.Objects;

/**
 * Date:2021/4/16
 * Author:fanshaofeng
 */
public class CheckedCommodity {
    private int commodity_id;
    private String cover;
    private String name;
    private double price;
    private int number;

    public CheckedCommodity(int commodity_id, String cover, String name, double price, int number) {
        this.commodity_id = commodity_id;
        this.cover = cover;
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public int getcommodity_id() {
        return commodity_id;
    }

    public void setcommodity_id(int commodity_id) {
        this.commodity_id = commodity_id;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "CheckedCommodity{" +
                "commodity_id=" + commodity_id +
                ", cover='" + cover + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckedCommodity that = (CheckedCommodity) o;
        return commodity_id == that.commodity_id &&
                Double.compare(that.price, price) == 0 &&
                number == that.number &&
                Objects.equals(cover, that.cover) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commodity_id, cover, name, price, number);
    }
}
