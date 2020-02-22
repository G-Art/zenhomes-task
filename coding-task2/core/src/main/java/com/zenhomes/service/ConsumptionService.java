package com.zenhomes.service;

import com.zenhomes.data.Village;

import java.util.Map;

public interface ConsumptionService {

	void addConsumption(Village village, double amount, String counter);

	Map<String, Double> getConsumption(String duration);
}
