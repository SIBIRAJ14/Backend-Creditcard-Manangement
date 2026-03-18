package com.banking.credit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.credit.bo.CountryBO;
import com.banking.credit.dto.CountryRequest;

@Service
public class CountryService {

    @Autowired
    private CountryBO countryBO;

    public List<CountryRequest> getAllCountries() {
        return countryBO.getAllCountries();
    }
}
