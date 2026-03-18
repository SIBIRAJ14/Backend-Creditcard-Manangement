package com.banking.credit.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "creditcards")
@EntityListeners(AuditingEntityListener.class)
@NamedQueries({
    @NamedQuery(name = "Creditcards.findAllCardsOrderByLimitDesc",query = "SELECT c FROM Creditcards c ORDER BY c.creditLimit DESC"),
    @NamedQuery(name = "Creditcards.findByStatus",query = "SELECT c FROM Creditcards c WHERE c.status = :status"),
    @NamedQuery(name = "Creditcards.findByCardType",query = "SELECT c FROM Creditcards c WHERE c.cardType = :cardType")})
public class Creditcards {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_id")
	private Integer cardId;
	
	@NotNull(message = "Customer ID is required")
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",nullable=false)
	private Customers customerId;
	
	
	@NotBlank(message = "Card type cannot be blank")
	@Column(name = "card_type", length = 20, nullable = false)
	private String cardType;
	@Column(name = "status", length = 20)
	private String status;
	@Column(name = "issue_date")
	private LocalDate issueDate;
	private BigDecimal creditLimit;
	
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;
	
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public Customers getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Customers customerId) {
		this.customerId = customerId;
	}
	
	@JsonProperty("customerId")
	public Integer getCustomerIdValue() {
	    return customerId != null ? customerId.getCustomerId() : null;
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
	@Override
	public String toString() {
		return "Creditcards [cardId=" + cardId + ", customerId=" + customerId + ", cardType=" + cardType + ", status="
				+ status + ", issueDate=" + issueDate + ", creditLimit=" + creditLimit + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	
}
