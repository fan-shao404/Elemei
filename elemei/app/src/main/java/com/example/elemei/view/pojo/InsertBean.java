package com.example.elemei.view.pojo;

/**
 * Date:2021/4/11
 * Author:fanshaofeng
 */
public class InsertBean extends BaseBean {

    public OKPacket result;

    public InsertBean(boolean isResult) {
        super(isResult);
    }

    public InsertBean(boolean isResult, OKPacket okPacket) {
        super(isResult);
        this.result = okPacket;
    }

    public OKPacket getOkPacket() {
        return result;
    }

    public void setOkPacket(OKPacket okPacket) {
        this.result = okPacket;
    }

    @Override
    public String toString() {
        return "InsertBean{" +
                "okPacket=" + result +
                ", isResult=" + isResult +
                '}';
    }
}
