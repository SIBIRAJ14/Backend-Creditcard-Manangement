package com.banking.credit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.credit.dao.ICityDAO;
import com.banking.credit.dto.CityRequest;
import com.banking.credit.entity.City;
import com.banking.credit.service.CityService;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin("*")
public class CityController {

	@Autowired
    private CityService cityService;

    @GetMapping("/state/{stateId}")
    public List<CityRequest> getCitiesByState(@PathVariable Integer stateId) {
        return cityService.getCitiesByState(stateId);
    }
}
