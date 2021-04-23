package com.example.elemei.view.pojo;

/**
 * Date:2021/4/12
 * Author:fanshaofeng
 */
public class Store {
    private Integer id;
    private String name;
    private String cover;
    private double score;
    private double start_send;
    private double distribution;
    private String classification;

    public Store() {

    }

    public Store(Integer id,String name, String cover, double score, double start_send, double distribution, String classification) {
        this.id = id;
        this.name = name;
        this.cover = cover;
        this.score = score;
        this.start_send = start_send;
        this.distribution = distribution;
        this.classification = classification;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getStart_send() {
        return start_send;
    }

    public void setStart_send(double start_send) {
        this.start_send = start_send;
    }

    public double getDistribution() {
        return distribution;
    }

    public void setDistribution(double distribution) {
        this.distribution = distribution;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id="+id+
                "name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                ", score=" + score +
                ", start_send=" + start_send +
                ", distribution=" + distribution +
                ", classification='" + classification + '\'' +
                '}';
    }
}
