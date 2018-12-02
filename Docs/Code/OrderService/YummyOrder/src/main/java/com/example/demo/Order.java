package com.example.demo;

import java.util.List;

public class Order {
	private Long ID;
	private Long UserID;
	private Long StoreID;
	private Long AddressID;
	private int NumGoods;
	private List<Long> GoodsID;
	private int State;
	
	public void setID(Long id) {
		ID = id;
	}
	public Long getID() {
		return ID;
	}
	public void setUserID(Long userID) {
		UserID = userID;
	}
	public Long getUserID() {
		return UserID;
	}
	public void setStoreID(Long storeID) {
		StoreID = storeID;
	}
	public Long getStoreID() {
		return StoreID;
	}
	public void setAddressID(Long addressID) {
		AddressID = addressID;
	}
	public Long getAddressID() {
		return AddressID;
	}
	public void setNumGoods(int numGoods) {
		NumGoods = numGoods;
	}
	public int getNumGoods() {
		return NumGoods;
	}
	public void setGoodsInfo(List<Long> goodsID) {
		GoodsID = goodsID;
	}
	public List<Long> getGoodsID() {
		return GoodsID;
	}
	public void setState(int state) {
		State = state;
	}
	public int getState() {
		return State;
	}
}
