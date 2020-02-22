package com.zenhomes.service.impl;

import com.zenhomes.data.Consumption;
import com.zenhomes.data.Village;
import com.zenhomes.service.ConsumptionService;
import com.zenhomes.service.VillageService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultConsumptionService implements ConsumptionService {

	private VillageService villageService;

	public DefaultConsumptionService(VillageService villageService) {
		this.villageService = villageService;
	}

	@Override
	public void addConsumption(Village village, double amount, String counter) {
		final Consumption consumption = new Consumption(String.valueOf(village.getConsumptions().size()), counter, amount, village);
		village.addConsumption(consumption);
	}

	@Override
	public Map<String, Double> getConsumption(String duration) {
		var now = LocalDateTime.now();

		var value = duration.substring(0, duration.length() - 1);
		ChronoUnit unit;
		switch (duration.substring(value.length())) {
			case "s": {
				unit = ChronoUnit.SECONDS;
				break;
			}
			case "m": {
				unit = ChronoUnit.MINUTES;
				break;
			}
			case "h": {
				unit = ChronoUnit.HOURS;
				break;
			}
			case "d": {
				unit = ChronoUnit.DAYS;
				break;
			}
			case "M": {
				unit = ChronoUnit.MONTHS;
				break;
			}
			case "y": {
				unit = ChronoUnit.YEARS;
				break;
			}
			default: {
				unit = ChronoUnit.HOURS;
			}
		}

		var fromDate = now.minus(Long.parseLong(value), unit);
		return villageService.getAllVillages()
				.stream()
				.collect(Collectors.toMap(Village::getName,
						village -> village.getConsumptions()
						.stream()
						.filter(consumption -> consumption.getTimestamp().isAfter(fromDate))
						.mapToDouble(Consumption::getAmount)
						.sum(),
						Double::sum));
	}
}
