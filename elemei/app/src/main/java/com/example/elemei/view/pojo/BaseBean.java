package com.example.elemei.view.pojo;

/**
 * Date:2021/4/11
 * Author:fanshaofeng
 */
public class BaseBean {
    public boolean isResult;

    public BaseBean(boolean isResult) {
        this.isResult = isResult;
    }

    public boolean isResult() {
        return isResult;
    }

    public void setResult(boolean result) {
        isResult = result;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "isResult=" + isResult +
                '}';
    }
}
