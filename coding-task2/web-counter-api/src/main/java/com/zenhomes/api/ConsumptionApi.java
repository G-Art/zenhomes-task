package com.zenhomes.api;

import com.zenhomes.data.Consumption;
import com.zenhomes.data.Counter;
import com.zenhomes.data.Village;
import com.zenhomes.dto.ConsumptionDTO;
import com.zenhomes.dto.CounterCallbackRequest;
import com.zenhomes.dto.ReportDTO;
import com.zenhomes.service.ConsumptionService;
import com.zenhomes.service.CounterService;
import com.zenhomes.service.VillageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController("/")
public class ConsumptionApi {

	@Resource
	private ConsumptionService consumptionService;

	@Resource
	private CounterService counterService;

	@Resource
	private VillageService villageService;

	@PostMapping(value = "/counter_callback")
	@ResponseBody
	public ResponseEntity<Object> counterCallback(@RequestBody CounterCallbackRequest counterCallbackRequest) {

		var counter = counterService.getCounterById(counterCallbackRequest.getCounter_id());
		if(Objects.isNull(counter)){
			return ResponseEntity.notFound().build();
		}else {
			var village = villageService.getVillageForCounter(counter);
			consumptionService.addConsumption(village, counterCallbackRequest.getAmount(), counter.getId());
			return ResponseEntity.ok().build();
		}

	}

	@GetMapping("/consumption_report")
	public ReportDTO getCounter(@RequestParam("duration") String duration) {

		final Map<String, Double> dtos = consumptionService.getConsumption(duration);

		final List<ConsumptionDTO> list = new ArrayList<>();

		for (Map.Entry<String, Double> consumption : dtos.entrySet()) {
			final ConsumptionDTO consumptionDto = new ConsumptionDTO();
			consumptionDto.setVillage_name(consumption.getKey());
			consumptionDto.setConsumption(consumption.getValue());

			list.add(consumptionDto);
		}
		var result = new ReportDTO();
		result.setVillages(list);
		return result;
	}
}
