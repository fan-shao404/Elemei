package com.example.elemei.view.pojo;

/**
 * Date:2021/4/16
 * Author:fanshaofeng
 */
public class CheckedCommodity {
    private Integer commodity_id;
    private String cover;
    private String name;
    private Double price;
    private Integer number;

    public CheckedCommodity(Integer commodity_id, String cover, String name, Double price, Integer number) {
        this.commodity_id = commodity_id;
        this.cover = cover;
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public Integer getcommodity_id() {
        return commodity_id;
    }

    public void setcommodity_id(Integer commodity_id) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
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
}
