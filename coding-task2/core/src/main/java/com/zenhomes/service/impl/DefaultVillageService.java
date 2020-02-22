package com.zenhomes.service.impl;

import com.zenhomes.data.Counter;
import com.zenhomes.data.Village;
import com.zenhomes.repository.VillageRepository;
import com.zenhomes.service.VillageService;

import java.util.List;
import java.util.Objects;

public class DefaultVillageService implements VillageService {

	 private VillageRepository villageRepository;

	public DefaultVillageService(VillageRepository villageRepository) {
		this.villageRepository = villageRepository;
	}

	@Override
	public Village getVillageForCounter(Counter counter){
		var village = villageRepository.getVillageById(counter.getVillage().getId());
		if(Objects.isNull(village)){
			village = counter.getVillage();
			villageRepository.save(village);
		}
		return village;
	}

	@Override
	public List<Village> getAllVillages(){
		return List.copyOf(villageRepository.getAllVillages());
	}
}
