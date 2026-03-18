package com.banking.credit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.credit.entity.State;
@Repository
public interface IStateDAO extends JpaRepository<State,Integer> {

	List<State> findByCountry_CountryId(Integer countryId);



}
