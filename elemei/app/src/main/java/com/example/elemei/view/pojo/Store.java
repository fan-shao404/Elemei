package com.example.elemei.view.pojo;

/**
 * Date:2021/4/12
 * Author:fanshaofeng
 */
public class Store {
    private String name;
    private String cover;
    private double score;
    private String start_send;
    private String distribution;
    private String classification;

    public Store(String name, String cover, double score, String start_send, String distribution, String classification) {
        this.name = name;
        this.cover = cover;
        this.score = score;
        this.start_send = start_send;
        this.distribution = distribution;
        this.classification = classification;
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

    public String getStart_send() {
        return start_send;
    }

    public void setStart_send(String start_send) {
        this.start_send = start_send;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
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
                "name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                ", score=" + score +
                ", start_send='" + start_send + '\'' +
                ", distribution='" + distribution + '\'' +
                ", classification='" + classification + '\'' +
                '}';
    }
}
