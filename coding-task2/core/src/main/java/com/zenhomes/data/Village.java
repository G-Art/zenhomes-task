package com.zenhomes.data;

import java.util.ArrayList;
import java.util.List;

public class Village {

	private String id;

	private String name;

	private List<Consumption> consumptions = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Consumption> getConsumptions() {
		return consumptions;
	}


	public void addConsumption(Consumption consumption) {
		consumptions.add(consumption);
	}
}
