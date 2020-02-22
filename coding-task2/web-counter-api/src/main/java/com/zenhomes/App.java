package com.zenhomes;

import com.zenhomes.repository.VillageRepository;
import com.zenhomes.repository.impl.MockVillageRepository;
import com.zenhomes.service.ConsumptionService;
import com.zenhomes.service.CounterService;
import com.zenhomes.service.VillageService;
import com.zenhomes.service.impl.DefaultConsumptionService;
import com.zenhomes.service.impl.DefaultCounterService;
import com.zenhomes.service.impl.DefaultVillageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public ConsumptionService consumptionService(){
		return new DefaultConsumptionService(villageService());
	}

	@Bean
	public CounterService counterService(){
		return new DefaultCounterService();
	}

	@Bean
	public VillageRepository villageRepository(){
		return new MockVillageRepository();
	}

	@Bean
	public VillageService villageService(){
		return new DefaultVillageService(villageRepository());
	}
}
