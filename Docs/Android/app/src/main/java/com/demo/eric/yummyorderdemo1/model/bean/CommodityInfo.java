package com.demo.eric.yummyorderdemo1.model.bean;

import java.io.Serializable;

public class CommodityInfo implements Serializable{
    int id;

    String name;

    int storeId;

    String typeId;

    String description;

    double orPrice;

    double cuPrice;

    String imagePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typename) {
        this.typeId = typename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getOrPrice() {
        return orPrice;
    }

    public void setOrPrice(double onPrice) {
        this.orPrice = onPrice;
    }

    public double getCuPrice() {
        return cuPrice;
    }

    public void setCuPrice(double cuPrice) {
        this.cuPrice = cuPrice;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
