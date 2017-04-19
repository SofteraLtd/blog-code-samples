package io.softera.samples.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.softera.samples.model.Order;

@RestController
@RequestMapping("api/v1/")
public class OrderController {
	
	@RequestMapping(value = "orders", method = RequestMethod.GET)
	public List<Order> list() {
		return OrderStub.list();
	}
	
	@RequestMapping(value = "orders/{id}", method = RequestMethod.GET)
	public Order get(@PathVariable Long id) {
		return OrderStub.get(id);
	}
	
	@RequestMapping(value = "fallbacks/orders", method = RequestMethod.GET)
	public Order fallbackGet() {
		return OrderStub.get(1L);//defaults to 1
	}
}
