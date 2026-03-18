package com.banking.credit.dto;

public class CityRequest {
	private Integer cityId;
	private String city;
	private Integer stateId;
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	
	@Override
	public String toString() {
		return "CityRequest [cityId=" + cityId + ", city=" + city + ", stateId=" + stateId + "]";
	}
	

}
