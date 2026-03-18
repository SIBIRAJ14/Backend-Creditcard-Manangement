package com.banking.credit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banking.credit.entity.Customers;
@Repository
public interface ICustomerDAO extends JpaRepository<Customers,Integer>{
	
	@Modifying
	@Query(value = "DELETE FROM customers WHERE customer_id = :id", nativeQuery = true)
	int deleteCustomerNative(@Param("id") Integer id);



}
