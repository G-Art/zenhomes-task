package com.zenhomes.repository;

import com.zenhomes.data.Village;

import java.util.Collection;

public interface VillageRepository {

	Village save(Village village);

	Village getVillageById(String id);

	Collection<Village> getAllVillages();

}
