package com.example.elemei.view.pojo;

import java.util.List;

/**
 * Date:2021/4/16
 * Author:fanshaofeng
 */
public class CheckedCommmodityBean extends BaseBean {

    private List<CheckedCommodity> result;

    public CheckedCommmodityBean(boolean isResult, List<CheckedCommodity> result) {
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
        return "CheckedCommmodityBean{" +
                "result=" + result +
                ", isResult=" + isResult +
                '}';
    }

    public CheckedCommmodityBean(boolean isResult) {
        super(isResult);
    }
}
