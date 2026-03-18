package com.banking.credit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.banking.credit.dto.CustomerRequest;
import com.banking.credit.entity.Customers;
import com.banking.credit.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Add customer
    @PostMapping("/add")
    public ResponseEntity<Customers> addCustomer(
            @RequestBody CustomerRequest customer) {

        Customers saved = customerService.addCustomer(customer);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Get customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerRequest> getCustomerById(
            @PathVariable Integer id) {

        CustomerRequest customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    // Get all customers
    @GetMapping
    public ResponseEntity<List<CustomerRequest>> getAllCustomers() {

        List<CustomerRequest> customers =
                customerService.getAllCustomers();

        return ResponseEntity.ok(customers);
    }

    // Delete customer
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable Integer id) {

        customerService.deleteCustomer(id);

        return ResponseEntity.ok(
                "Customer deleted successfully"
        );
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<Customers> updateCustomer(
            @PathVariable Integer id,
            @RequestBody CustomerRequest customer) {

        Customers updated =
                customerService.updateCustomer(id, customer);

        return ResponseEntity.ok(updated);
    }
}
