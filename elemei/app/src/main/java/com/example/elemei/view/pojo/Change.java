package com.example.elemei.view.pojo;

/**
 * Date:2021/4/17
 * Author:fanshaofeng
 */
public class Change {

    private CheckedCommodity CheckedCommodity;
    private Operation operation;

    public Change(CheckedCommodity CheckedCommodity, Operation operation) {
        this.CheckedCommodity = CheckedCommodity;
        this.operation = operation;
    }

    public CheckedCommodity getCheckedCommodity() {
        return CheckedCommodity;
    }

    public void setCheckedCommodity(CheckedCommodity CheckedCommodity) {
        this.CheckedCommodity = CheckedCommodity;
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
        DELETE,
        EMPTY
    }
}
