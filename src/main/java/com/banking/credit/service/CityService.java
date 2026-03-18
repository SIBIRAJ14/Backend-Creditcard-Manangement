package com.banking.credit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.credit.bo.CityBO;
import com.banking.credit.dto.CityRequest;

@Service
public class CityService {

    @Autowired
    private CityBO cityBO;

    public List<CityRequest> getCitiesByState(Integer stateId) {
        return cityBO.getCitiesByState(stateId);
    }
}
