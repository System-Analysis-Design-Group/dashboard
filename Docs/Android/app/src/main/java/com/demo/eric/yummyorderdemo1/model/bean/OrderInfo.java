package com.demo.eric.yummyorderdemo1.model.bean;

import java.util.List;

public class OrderInfo {
    int userID;

    int storeID;

    int addressID;

    int numGoods;

    String date;

    int state;

    String id;

    List<GoodsInfo> goodsInfo;

    public OrderInfo(int addressID){
        this.addressID=addressID;
    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getNumGoods() {
        return numGoods;
    }

    public void setNumGoods(int numGoods) {
        this.numGoods = numGoods;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<GoodsInfo> getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(List<GoodsInfo> goodsInfo) {
        this.goodsInfo = goodsInfo;
    }
}
