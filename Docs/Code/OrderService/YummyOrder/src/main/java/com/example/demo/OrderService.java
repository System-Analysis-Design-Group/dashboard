package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/orders")
public class OrderService {
	
	@PostMapping
	public JSONObject createOrder(@RequestBody JSONObject jsonParam) {
		
		JSONObject result = new JSONObject();
		// request mysql
		// if success
		return result;
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public JSONObject getOrder(@PathVariable("id") int id) {
		JSONObject result = new JSONObject();
		// request mysql
		// if success
		return result;
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public JSONObject deleteOrder(@PathVariable("id") int id) {
		JSONObject result = new JSONObject();
		// request mysql
		// if success
		return result;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public JSONObject updateOrder(@RequestBody JSONObject jsonParam) {
		
		JSONObject result = new JSONObject();
		// request mysql
		// if success
		return result;
	}
}
