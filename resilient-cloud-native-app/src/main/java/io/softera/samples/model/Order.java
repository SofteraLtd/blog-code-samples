package io.softera.samples.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
	
	private Long orderId;
	
	private Double orderAmount;
	
	private LocalDate orderDate;
	
	@JsonCreator
	public Order(@JsonProperty("orderId") Long orderId, 
				 @JsonProperty("orderAmount") Double orderAmount, 
				 @JsonProperty("orderDate") LocalDate orderDate) {

		this.orderId = orderId;
		this.orderAmount = orderAmount;
		this.orderDate = orderDate;
	}

	public Long getOrderId() {
		return orderId;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderAmount=" + orderAmount + ", orderDate=" + orderDate + "]";
	}	
}
