package com.banking.credit.dao;

import java.math.BigDecimal;
import java.time.LocalDate;



public interface CreditCustomized {
	    Integer getCardId();
	    String getCardType();
	    String getStatus();
	    BigDecimal getCreditLimit();
	    LocalDate getIssueDate();
	    Integer getCustomerId();
	}


