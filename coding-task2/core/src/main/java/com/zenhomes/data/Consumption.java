package com.zenhomes.data;

import java.time.LocalDateTime;
import java.util.Date;

public class Consumption {

	private String id;

	private String counter;

	private double amount;

	private LocalDateTime timestamp;

	private Village village;

	public Consumption(String id, String counter, double amount, Village village) {
		super();
		this.id = id;
		this.counter = counter;
		this.amount = amount;
		this.timestamp = LocalDateTime.now();
		this.village = village;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getCounter() {
		return counter;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Consumption)) return false;

		Consumption that = (Consumption) o;

		if (Double.compare(that.getAmount(), getAmount()) != 0) return false;
		if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
		if (getCounter() != null ? !getCounter().equals(that.getCounter()) : that.getCounter() != null) return false;
		if (getTimestamp() != null ? !getTimestamp().equals(that.getTimestamp()) : that.getTimestamp() != null)
			return false;
		return getVillage() != null ? getVillage().equals(that.getVillage()) : that.getVillage() == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (getCounter() != null ? getCounter().hashCode() : 0);
		temp = Double.doubleToLongBits(getAmount());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (getTimestamp() != null ? getTimestamp().hashCode() : 0);
		result = 31 * result + (getVillage() != null ? getVillage().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Consumption{" +
				"id=" + id +
				", counter=" + counter +
				", amount=" + amount +
				", timestamp=" + timestamp +
				", village=" + village +
				'}';
	}
}
