package com.example.elemei.view.pojo;

import java.util.List;

/**
 * Date:2021/4/12
 * Author:fanshaofeng
 */
public class CountBean extends BaseBean {

    private List<RowDataPacket> result;

    public CountBean(boolean isResult) {
        super(isResult);
    }

    public CountBean(boolean isResult, List<RowDataPacket> result) {
        super(isResult);
        this.result = result;
    }

    public List<RowDataPacket> getResult() {
        return result;
    }

    public void setResult(List<RowDataPacket> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CountBean{" +
                "result=" + result +
                ", isResult=" + isResult +
                '}';
    }
}
