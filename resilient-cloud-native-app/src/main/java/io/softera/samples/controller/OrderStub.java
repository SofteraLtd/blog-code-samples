package io.softera.samples.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import io.softera.samples.model.Order;

public class OrderStub {
	
	protected static AtomicLong nextOrderId = new AtomicLong();
	
	private static Map<Long, Order> orders = new HashMap<>();
	
	static {
		Order o1 = new Order(nextOrderId.incrementAndGet(), new Double(100), LocalDate.now());
		orders.put(o1.getOrderId(), o1);
		Order o2 = new Order(nextOrderId.incrementAndGet(), new Double(20.5), LocalDate.now().minus(1, ChronoUnit.DAYS));
		orders.put(o2.getOrderId(), o2);
		Order o3 = new Order(nextOrderId.incrementAndGet(), new Double(169), LocalDate.now().minus(2, ChronoUnit.DAYS));
		orders.put(o3.getOrderId(), o3);
		Order o4 = new Order(nextOrderId.incrementAndGet(), new Double(199.9), LocalDate.now().minus(1, ChronoUnit.WEEKS));
		orders.put(o4.getOrderId(), o4);
	}
	
	public static List<Order> list() {
		return new ArrayList<Order>(orders.values());
	}
	
	public static Order get(Long id) {
		return orders.get(id);
	}
}
