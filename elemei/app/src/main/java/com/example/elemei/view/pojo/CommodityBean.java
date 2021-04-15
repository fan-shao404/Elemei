package com.example.elemei.view.pojo;

import java.util.List;

/**
 * Date:2021/4/15
 * Author:fanshaofeng
 */
public class CommodityBean extends BaseBean {

    private List<Commodity> result;

    @Override
    public String toString() {
        return "CommodityBean{" +
                "result=" + result +
                ", isResult=" + isResult +
                '}';
    }

    public List<Commodity> getResult() {
        return result;
    }

    public void setResult(List<Commodity> result) {
        this.result = result;
    }

    public CommodityBean(boolean isResult, List<Commodity> result) {
        super(isResult);
        this.result = result;
    }

    public CommodityBean(boolean isResult) {
        super(isResult);
    }
}
