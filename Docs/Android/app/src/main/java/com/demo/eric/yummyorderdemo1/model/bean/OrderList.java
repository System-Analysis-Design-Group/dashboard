package com.demo.eric.yummyorderdemo1.model.bean;

import java.util.List;

public class OrderList {
    String code;

    String message;

    List<OrderInfo> obj;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OrderInfo> getObj() {
        return obj;
    }

    public void setObj(List<OrderInfo> obj) {
        this.obj = obj;
    }
}
