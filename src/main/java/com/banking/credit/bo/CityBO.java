package com.banking.credit.bo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking.credit.dao.ICityDAO;
import com.banking.credit.dto.CityRequest;

@Component
public class CityBO {
	
	@Autowired
	private ICityDAO cityRepo;
	
	public List<CityRequest> getCitiesByState(Integer stateId) {
		return cityRepo.findByState_StateId(stateId).stream().map(entity -> {
			CityRequest dto = new CityRequest();
			dto.setCityId(entity.getCityId());
			dto.setCity(entity.getCity());
			dto.setStateId(entity.getState() != null ? entity.getState().getStateId() : null);
			return dto;
		}).collect(Collectors.toList());
	}

}
