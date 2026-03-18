package com.banking.credit.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "countrymaster")
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer countryId;
	private String country;
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<State> states;
	/*
	 * @JsonManagedReference
	 * 
	 * @OneToMany(fetch=FetchType.EAGER,mappedBy = "countryId", cascade =
	 * CascadeType.ALL) private List<Customers> customers;
	 */
	
	/*
	 * public List<Customers> getCustomers() { return customers; } public void
	 * setCustomers(List<Customers> customers) { this.customers = customers; }
	 */
	 
	public Integer getCountryId() {
		return countryId;
	}
	public List<State> getStates() {
		return states;
	}
	public void setStates(List<State> states) {
		this.states = states;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", country=" + country + "]";
	}
	

}
