package com.example.elemei.view.pojo;

/**
 * Date:2021/4/12
 * Author:fanshaofeng
 */
public class RowDataPacket {
    private Integer count;

    public RowDataPacket(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "RowDataPacket{" +
                "count=" + count +
                '}';
    }
}
