package com.banking.credit.dto;

public class CountryRequest {
	private Integer countryId;
	private String country;
	public Integer getCountryId() {
		return countryId;
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
		return "CountryRequest [countryId=" + countryId + ", country=" + country + "]";
	}
	

}
