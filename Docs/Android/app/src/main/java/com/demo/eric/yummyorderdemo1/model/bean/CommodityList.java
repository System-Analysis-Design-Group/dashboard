package com.demo.eric.yummyorderdemo1.model.bean;

import java.io.Serializable;
import java.util.List;

public class CommodityList implements Serializable{
    String code;

    String message;

    List<CommodityInfo> obj;

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

    public List<CommodityInfo> getObj() {
        return obj;
    }

    public void setObj(List<CommodityInfo> obj) {
        this.obj = obj;
    }
}
