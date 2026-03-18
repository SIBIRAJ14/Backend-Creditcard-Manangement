package com.banking.credit.bo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking.credit.dao.IStateDAO;
import com.banking.credit.dto.StateRequest;

@Component
public class StateBO {
	@Autowired
	private IStateDAO stateRepo;
	
	public List<StateRequest> getStatesByCountry(Integer countryId) {
		return stateRepo.findByCountry_CountryId(countryId).stream().map(entity -> {
			StateRequest dto = new StateRequest();
			dto.setStateId(entity.getStateId());
			dto.setState(entity.getState());
			dto.setCountryId(entity.getCountry() != null ? entity.getCountry().getCountryId() : null);
			return dto;
		}).collect(Collectors.toList());
	}

}
