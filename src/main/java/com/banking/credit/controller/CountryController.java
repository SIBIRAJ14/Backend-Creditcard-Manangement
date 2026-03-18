package com.banking.credit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.credit.dao.ICountryDAO;
import com.banking.credit.dto.CountryRequest;
import com.banking.credit.entity.Country;
import com.banking.credit.service.CountryService;
@RestController
@RequestMapping("/api/countries")
@CrossOrigin("*")
public class CountryController {

	@Autowired
    private CountryService countryService;

    @GetMapping
    public List<CountryRequest> getAllCountries() {
        return countryService.getAllCountries();
    }
}

