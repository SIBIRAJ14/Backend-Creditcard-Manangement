package com.banking.credit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.banking.credit.entity.City;
@Repository
public interface ICityDAO extends JpaRepository<City,Integer>{

	List<City> findByState_StateId(Integer stateId);

}
