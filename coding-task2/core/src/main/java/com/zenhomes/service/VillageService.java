package com.zenhomes.service;

import com.zenhomes.data.Counter;
import com.zenhomes.data.Village;

import java.util.List;

public interface VillageService {
	Village getVillageForCounter(Counter counter);

	List<Village> getAllVillages();
}
