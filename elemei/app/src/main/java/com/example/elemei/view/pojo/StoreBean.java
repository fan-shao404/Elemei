package com.example.elemei.view.pojo;

import java.util.List;

/**
 * Date:2021/4/13
 * Author:fanshaofeng
 */
public class StoreBean extends BaseBean {

    private List<Store> result;

    public StoreBean(boolean isResult) {
        super(isResult);
    }

    @Override
    public String toString() {
        return "StoreBean{" +
                "result=" + result +
                ", isResult=" + isResult +
                '}';
    }

    public List<Store> getResult() {
        return result;
    }

    public void setResult(List<Store> result) {
        this.result = result;
    }

    public StoreBean(boolean isResult, List<Store> result) {
        super(isResult);
        this.result = result;
    }
}
