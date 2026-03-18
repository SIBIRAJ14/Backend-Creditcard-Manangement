package com.banking.credit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking.credit.bo.CustomerBO;
import com.banking.credit.dto.CustomerRequest;
import com.banking.credit.entity.Customers;

@Component
public class CustomerService {
	
	@Autowired
    private CustomerBO customerBO;
	
	public Customers addCustomer(CustomerRequest customer) {
        return customerBO.insert(customer);
    }

    // Get customer by ID
	public CustomerRequest getCustomerById(Integer id) {
	    return customerBO.findById(id);
	}

    // Get all customers
    public List<CustomerRequest> getAllCustomers() {
        return customerBO.findAll();
    }

    // Delete customer
    public void deleteCustomer(Integer customerId) {
        customerBO.deleteById(customerId);
    }
    public Customers updateCustomer(Integer customerId, CustomerRequest customer) {
        return customerBO.updateCustomer(customerId, customer);
    }

	

}
