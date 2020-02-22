package com.zenhomes.repository.impl;

import com.zenhomes.data.Village;
import com.zenhomes.repository.VillageRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockVillageRepository implements VillageRepository {

	private Map<String, Village> storage = new HashMap<>();

	@Override
	public Village save(Village village){
		storage.put(village.getId(), village);
		return village;
	}

	@Override
	public Village getVillageById(String id) {
		return storage.get(id);
	}

	@Override
	public Collection<Village> getAllVillages() {
		return storage.values();
	}
}
