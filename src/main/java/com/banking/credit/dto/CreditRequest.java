package com.banking.credit.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class CreditRequest {

    public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	private Integer cardId;

    @NotNull(message = "Customer ID is required")
    private Integer customerId;
    @NotBlank(message = "Card type cannot be blank")
    private String cardType;
    @NotBlank(message = "Status cannot be blank")
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Issue date cannot be in the future")
    @NotNull(message = "Issue date is required")
    private LocalDate issueDate;

    public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	@NotNull(message = "Credit limit is required")
	@DecimalMin(value = "1000.00", message = "Credit limit must be greater than 1000")
    private BigDecimal creditLimit;
    
    

	
}
