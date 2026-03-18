package com.banking.credit.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking.credit.dao.ICityDAO;
import com.banking.credit.dao.ICountryDAO;
import com.banking.credit.dao.ICreditDAO;
import com.banking.credit.dao.ICustomerDAO;
import com.banking.credit.dao.IStateDAO;
import com.banking.credit.dto.CustomerRequest;
import com.banking.credit.entity.City;
import com.banking.credit.entity.Country;
import com.banking.credit.entity.Customers;
import com.banking.credit.entity.State;
import com.banking.credit.exception.CreditCardException;
import com.banking.credit.exception.CustomerException;

import jakarta.transaction.Transactional;

@Component
public class CustomerBO {
	@Autowired
	private ICustomerDAO cr;
	@Autowired
	private ICreditDAO creditDAO;

	
	@Autowired
	private ICountryDAO countryDAO;
	@Autowired
	private IStateDAO stateDAO;
	@Autowired
	private ICityDAO cityDAO;
	
	public Customers insert(CustomerRequest request) {

	    if (request.getName() == null || request.getName().isBlank() ||
	        !request.getName().matches("^[A-Za-z ]+$")) {
	        throw new CustomerException(
	            "Name must contain only alphabets and spaces"
	        );
	    }

	    if (request.getEmail() == null || request.getEmail().isBlank()) {
	        throw new CustomerException("Email must not be blank");
	    }

	    
	    if (request.getPhone() == null) {
	        throw new CustomerException("Phone number is required");
	    }
	    if (String.valueOf(request.getPhone()).length() != 10) {
	        throw new CustomerException("Phone number must be 10 digits");
	    }
	    if (request.getDateOfBirth() != null &&
	        request.getDateOfBirth().isAfter(LocalDate.now())) {
	        throw new CustomerException(
	            "Date of birth cannot be in the future"
	        );
	    }

	    if (request.getCountryId() == null) {
	        throw new CustomerException("Country must be provided");
	    }
	    if (request.getStateId() == null) {
	        throw new CustomerException("State must be provided");
	    }
	    if (request.getCityId() == null) {
	        throw new CustomerException("City must be provided");
	    }


	    Country country = countryDAO.findById(request.getCountryId())
	            .orElseThrow(() -> new CustomerException("Invalid country ID"));

	    State state = stateDAO.findById(request.getStateId())
	            .orElseThrow(() -> new CustomerException("Invalid state ID"));

	    City city = cityDAO.findById(request.getCityId())
	            .orElseThrow(() -> new CustomerException("Invalid city ID"));


	    Customers customer = new Customers();
	    customer.setName(request.getName());
	    customer.setEmail(request.getEmail());
	    customer.setPhone(request.getPhone());
	    customer.setDateOfBirth(request.getDateOfBirth());

	    customer.setCountryId(country);
	    customer.setStateId(state);
	    customer.setCityId(city);

	    return cr.save(customer);
	}


	public CustomerRequest findById(Integer id) {

	    Customers customer = cr.findById(id)
	            .orElseThrow(() ->
	                    new CustomerException("Customer not found with id: " + id));

	    CustomerRequest response = new CustomerRequest();

	    response.setCustomerId(customer.getCustomerId());
	    response.setName(customer.getName());
	    response.setEmail(customer.getEmail());
	    response.setPhone(customer.getPhone());
	    response.setDateOfBirth(customer.getDateOfBirth());

	    response.setCountryId(customer.getCountryId().getCountryId());
	    response.setStateId(customer.getStateId().getStateId());
	    response.setCityId(customer.getCityId().getCityId());

	    // 🔥 ADD THIS
	    response.setCountry(customer.getCountryId().getCountry());
	    response.setState(customer.getStateId().getState());
	    response.setCity(customer.getCityId().getCity());

	    return response;
	}



	public List<CustomerRequest> findAll() {

	    List<Customers> customers = cr.findAll();

	    List<CustomerRequest> responseList = new ArrayList<>();

	    for (Customers customer : customers) {

	        CustomerRequest dto = new CustomerRequest();

	        dto.setCustomerId(customer.getCustomerId());
	        dto.setName(customer.getName());
	        dto.setEmail(customer.getEmail());
	        dto.setPhone(customer.getPhone());
	        dto.setDateOfBirth(customer.getDateOfBirth());

	        dto.setCountryId(customer.getCountryId().getCountryId());
	        dto.setStateId(customer.getStateId().getStateId());
	        dto.setCityId(customer.getCityId().getCityId());

	        // 🔥 ADD NAME MAPPING
	        dto.setCountry(customer.getCountryId().getCountry());
	        dto.setState(customer.getStateId().getState());
	        dto.setCity(customer.getCityId().getCity());

	        responseList.add(dto);
	    }

	    return responseList;
	}


	@Transactional
	public void deleteById(Integer id) {
		boolean hasCards = creditDAO.existsByCustomerId_CustomerId(id);

	    if (hasCards) {
	        throw new CustomerException(
	            "Cannot delete customer. Credit cards exist for customer id: " + id
	        );
	    }

	    int rows = cr.deleteCustomerNative(id);

	    if (rows == 0) {
	        throw new CustomerException("Customer not found or delete failed for id: " + id);
	    }

	    System.out.println("Customer deleted successfully: " + id);
	}

    public Customers updateCustomer(Integer customerId, CustomerRequest request) {

        if (customerId == null) {
            throw new IllegalArgumentException("Customer ID must not be null");
        }

        Customers existing = cr.findById(customerId)
                .orElseThrow(() ->
                        new CustomerException("Customer not found with id: " + customerId));

        if (request.getName() == null || request.getName().isBlank() ||
        	    !request.getName().matches("^[A-Za-z ]+$")) {
        	    throw new IllegalArgumentException(
        	        "Name must contain only alphabets and spaces"
        	    );
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email must not be blank");
        }

        if (request.getPhone() == null) {
            throw new IllegalArgumentException("Phone number is required");
        }

        if (request.getDateOfBirth() != null &&
                request.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }

        if (request.getCountryId() == null) {
            throw new IllegalArgumentException("Country must be provided");
        }

        if (request.getStateId() == null) {
            throw new IllegalArgumentException("State must be provided");
        }

        if (request.getCityId() == null) {
            throw new IllegalArgumentException("City must be provided");
        }

        existing.setName(request.getName());
        existing.setEmail(request.getEmail());
        existing.setPhone(request.getPhone());
        existing.setDateOfBirth(request.getDateOfBirth());
        Country country = new Country();
        country.setCountryId(request.getCountryId());

        State state = new State();
        state.setStateId(request.getStateId());

        City city = new City();
        city.setCityId(request.getCityId());
        existing.setCountryId(country);
        existing.setStateId(state);
        existing.setCityId(city);
        return cr.save(existing);
    }


}