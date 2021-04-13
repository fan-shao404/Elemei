package com.example.elemei.view.pojo;

import java.util.List;

/**
 * Date:2021/4/9
 * Author:fanshaofeng
 */
public class CustomerBean extends BaseBean {
    private List<Customer> result;

    public CustomerBean(boolean isResult) {
        super(isResult);
    }

    public CustomerBean(boolean isResult, List<Customer> result) {
        super(isResult);
        this.result = result;
    }

    public List<Customer> getResult() {
        return result;
    }

    public void setResult(List<Customer> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CustomerBean{" +
                "result=" + result +
                ", isResult=" + isResult +
                '}';
    }
}
