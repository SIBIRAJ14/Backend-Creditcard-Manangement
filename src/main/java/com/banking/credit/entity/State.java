package com.banking.credit.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "statemaster")
public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer stateId;
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	/*
	 * public List<Customers> getCustomers() { return customers; } public void
	 * setCustomers(List<Customers> customers) { this.customers = customers; }
	 */

	private String state;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;

	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
	private List<City> cities;
	
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	/*
	 * @JsonManagedReference
	 * 
	 * @OneToMany(fetch=FetchType.EAGER,mappedBy = "stateId", cascade =
	 * CascadeType.ALL) private List<Customers> customers;
	 */
	@Override
	public String toString() {
		return "State [stateId=" + stateId + ", state=" + state + "]";
	}
	
	

}
