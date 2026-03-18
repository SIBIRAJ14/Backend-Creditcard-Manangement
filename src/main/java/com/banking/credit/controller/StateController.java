package com.banking.credit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.credit.dao.IStateDAO;
import com.banking.credit.dto.StateRequest;
import com.banking.credit.entity.State;
import com.banking.credit.service.StateService;

@RestController
@RequestMapping("/api/states")
@CrossOrigin("*")
public class StateController {

	@Autowired
    private StateService stateService;

    @GetMapping("/country/{countryId}")
    public List<StateRequest> getStatesByCountry(@PathVariable Integer countryId) {
        return stateService.getStatesByCountry(countryId);
    }
}
