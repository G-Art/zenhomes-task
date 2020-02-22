package com.zenhomes.dto;

import java.util.List;

public class ReportDTO {
	private List<ConsumptionDTO> villages;

	public List<ConsumptionDTO> getVillages() {
		return villages;
	}

	public void setVillages(List<ConsumptionDTO> villages) {
		this.villages = villages;
	}
}
