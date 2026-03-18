package com.banking.credit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.credit.bo.StateBO;
import com.banking.credit.dto.StateRequest;

@Service
public class StateService {

    @Autowired
    private StateBO stateBO;

    public List<StateRequest> getStatesByCountry(Integer countryId) {
        return stateBO.getStatesByCountry(countryId);
    }
}
