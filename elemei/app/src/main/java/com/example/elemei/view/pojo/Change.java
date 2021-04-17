package com.example.elemei.view.pojo;

/**
 * Date:2021/4/17
 * Author:fanshaofeng
 */
public class Change {

    private CheckedCommodity checkedCommodity;
    private Operation operation;

    public Change(CheckedCommodity checkedCommodity, Operation operation) {
        this.checkedCommodity = checkedCommodity;
        this.operation = operation;
    }

    public CheckedCommodity getCheckedCommodity() {
        return checkedCommodity;
    }

    public void setCheckedCommodity(CheckedCommodity checkedCommodity) {
        this.checkedCommodity = checkedCommodity;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public enum  Operation{
        ADD,
        SUBTRACT,
        INSERT,
        DELETE
    }
}
