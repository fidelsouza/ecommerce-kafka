package com.job.ecommerce;

public class Order {
	private String id;
	private double value;
	
	public Order() {
		super();
	}

	public Order(String id, double value) {
		super();
		this.id = id;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
