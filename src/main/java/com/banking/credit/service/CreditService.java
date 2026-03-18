package com.banking.credit.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.banking.credit.bo.CreditBO;
import com.banking.credit.bo.CustomerBO;
import com.banking.credit.dao.CreditCustomized;
import com.banking.credit.dao.CustomerCustomized;
import com.banking.credit.dto.CreditRequest;
import com.banking.credit.entity.Creditcards;
import com.banking.credit.entity.Customers;


@Component
public class CreditService {
	@Autowired
	private CreditBO bo1;
	@Autowired
	private CustomerBO bo2;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void transactionCard(Customers cu, CreditRequest cr) {
		System.out.println("card add before");
		bo1.insert(cr);
		System.out.println("card add after");
		System.out.println("customer add before");
		bo2.insert(cu);
		System.out.println("customer add after");
	}
	
	public List<Creditcards> findByStatus(String status){
		return bo1.findByStatus(status);
		
	}
	public List<Creditcards> findByCardType(String type){
		return bo1.findByCardType(type);
	}
	 public Creditcards insert(CreditRequest request) {
	        return bo1.insert(request);
	    }
	 public int updateCardStatus(Integer cardId, String status) {
	        return bo1.updateCardStatus(cardId, status);
	    }
	public Creditcards findCard(Integer cardId) {
		return bo1.findCard(cardId);
		
	}
	public List<CreditCustomized> fetchAll(){
		return bo1.fetchAll();
		
	}
	public void deleteByCardId(Integer cardId) {
		bo1.deleteByCardId(cardId);
		
	}

	public List<Creditcards> findCardsByCustomer(Integer customerId) {
		return bo1.findCardsByCustomer(customerId);
	}

	public Long countCardsByCustomer(Integer customerId) {
		return bo1.countCardsByCustomer(customerId);
	}

	public List<Creditcards> findCardsWithLimitGreaterThan(BigDecimal limit) {
		return bo1.findCardsWithLimitGreaterThan(limit);
	}

	public List<Creditcards> findCardsWithLimitLessThan(BigDecimal limit) {
		return bo1.findCardsWithLimitLessThan(limit);
	}

	public List<Creditcards> findAllCardsOrderByLimitDesc() {
		return bo1.findAllCardsOrderByLimitDesc();
	}

	public List<Creditcards> inRange(BigDecimal min, BigDecimal max) {
		return bo1.findCardsByLimitRange(min, max);
	}

	public BigDecimal maxLimit(Integer customerId) {
		return bo1.getMaxLimit(customerId);
	}

	public BigDecimal minLimit(Integer customerId) {
		return bo1.getMinLimit(customerId);
	}

    public List<Object[]> groupedTotals() {
        return bo1.groupedTotals();
    }

    public List<Object[]> highTotals(BigDecimal limit) {
        return bo1.highTotal(limit);
    }
    
    public List<CreditCustomized> getCardsByIssueDate(LocalDate issueDate) {
        return bo1.findCardsByIssueDate(issueDate);
    }
    public List<CustomerCustomized> getAllCustomersWithCards() {
        return bo1.getAllCustomersWithCards();
    }
}

