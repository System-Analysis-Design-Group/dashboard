package com.example.demo;

public class Goods {
	private Long GoodID;
	private int Num;
	private double UnitPrice;
	
	public void setGoodID(Long goodID) {
		GoodID = goodID;
	}
	public Long getGoodID() {
		return GoodID;
	}
	public void setNum(int num) {
		Num = num;
	}
	public int getNum() {
		return Num;
	}
	public void setUnitPrice(double unitPrice) {
		UnitPrice = unitPrice;
	}
	public double getUnitPrice() {
		return UnitPrice;
	}
}