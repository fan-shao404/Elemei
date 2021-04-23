package com.example.elemei.view.pojo;

import java.util.List;

/**
 * Date:2021/4/23
 * Author:fanshaofeng
 */
public class CheckedCommodityBean extends BaseBean {

    private List<CheckedCommodity> result;

    public CheckedCommodityBean(boolean isResult) {
        super(isResult);
    }

    public CheckedCommodityBean(boolean isResult, List<CheckedCommodity> result) {
        super(isResult);
        this.result = result;
    }

    public List<CheckedCommodity> getResult() {
        return result;
    }

    public void setResult(List<CheckedCommodity> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CheckedCommodityBean{" +
                "result=" + result +
                ", isResult=" + isResult +
                '}';
    }
}
