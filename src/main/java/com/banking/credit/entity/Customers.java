package com.banking.credit.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "customers")
@EntityListeners(AuditingEntityListener.class)
public class Customers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Integer customerId;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public State getStateId() {
		return stateId;
	}
	public void setStateId(State stateId) {
		this.stateId = stateId;
	}
	public Country getCountryId() {
		return countryId;
	}
	public void setCountryId(Country countryId) {
		this.countryId = countryId;
	}
	public City getCityId() {
		return cityId;
	}
	public void setCityId(City cityId) {
		this.cityId = cityId;
	}
	public List<Creditcards> getCreditcards() {
		return creditcards;
	}
	public void setCreditcards(List<Creditcards> creditcards) {
		this.creditcards = creditcards;
	}

	@NotBlank
	private String name;
	
	@Email
	@NotBlank
	@Column(unique = true)
	private String email;
	
	@Column(nullable = false,unique = true)
	private Long phone;
	
    private LocalDate dateOfBirth;
    
    @JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id",nullable=false)
    private Country countryId;
    
    @JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id",nullable=false)
    private State stateId;
    
    @JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id",nullable=false)
    private City cityId;
 
    @JsonIgnore
    @OneToMany(fetch=FetchType.EAGER,mappedBy = "customerId", cascade = CascadeType.ALL)
    private List<Creditcards> creditcards;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = true, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;
    
	@Override
	public String toString() {
		return "Customers [customerId=" + customerId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", dateOfBirth=" + dateOfBirth + ", countryId=" + countryId + ", stateId=" + stateId + ", cityId="
				+ cityId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}


}
