package com.zenhomes.dto;

public class CounterCallbackRequest {
	private String counter_id;
	private double amount;

	public String getCounter_id() {
		return counter_id;
	}

	public void setCounter_id(String counter_id) {
		this.counter_id = counter_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
