package com.example.demo;

import java.util.List;

public interface OrderDao {
	void createOrder(Order order);
	
	List<Order> findAllOrder();
}
