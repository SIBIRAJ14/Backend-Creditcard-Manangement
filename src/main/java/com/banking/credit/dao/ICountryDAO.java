package com.banking.credit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.credit.entity.Country;
@Repository
public interface ICountryDAO extends JpaRepository<Country,Integer> {

}
