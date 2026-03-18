package com.banking.credit.bo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking.credit.dao.ICountryDAO;
import com.banking.credit.dto.CountryRequest;

@Component
public class CountryBO {
	@Autowired
	private ICountryDAO countryRepo;
	
	public List<CountryRequest> getAllCountries() {
		return countryRepo.findAll().stream().map(entity -> {
			CountryRequest dto = new CountryRequest();
			dto.setCountryId(entity.getCountryId());
			dto.setCountry(entity.getCountry());
			return dto;
		}).collect(Collectors.toList());
	}


}
