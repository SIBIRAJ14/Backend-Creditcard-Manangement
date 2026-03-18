package com.banking.credit.bo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking.credit.dao.CreditCustomized;
import com.banking.credit.dao.CustomerCustomized;
import com.banking.credit.dao.ICreditDAO;
import com.banking.credit.dao.ICustomerDAO;
import com.banking.credit.dto.CreditRequest;
import com.banking.credit.entity.Creditcards;
import com.banking.credit.entity.Customers;
import com.banking.credit.exception.CreditCardException;

@Component
public class CreditBO {

    @Autowired
    private ICreditDAO cr;

    @Autowired
    private ICustomerDAO customerDAO;

    // Add Card
    public Creditcards insert(CreditRequest c) {

        if (c == null) {
            throw new CreditCardException("Credit request cannot be null");
        }

        Customers customer = customerDAO.findById(c.getCustomerId())
                .orElseThrow(() ->
                        new CreditCardException("Customer not found with ID: " + c.getCustomerId())
                );

        String cardType = c.getCardType().toLowerCase();

        BigDecimal creditLimit;

        switch (cardType) {
            case "silver":
                creditLimit = new BigDecimal("50000");
                break;
            case "gold":
                creditLimit = new BigDecimal("200000");
                break;
            case "platinum":
                creditLimit = new BigDecimal("500000");
                break;
            case "diamond":
                creditLimit = new BigDecimal("1000000");
                break;
            default:
                throw new CreditCardException("Invalid card type");
        }

        String status = (c.getStatus() == null) ? "active" : c.getStatus();

        Creditcards card = new Creditcards();
        card.setCustomerId(customer);
        card.setCardType(cardType);
        card.setStatus(status);
        card.setCreditLimit(creditLimit);
        card.setIssueDate(c.getIssueDate());

        return cr.save(card);
    }

    // Update Status
    public int updateCardStatus(Integer cardId, String status) {
        return cr.updateCardStatus(cardId, status);
    }

    // Get All
    public List<CreditCustomized> fetchAll() {
        return cr.fetchAllCustomized();
    }

    // Get By ID
    public Creditcards findCard(Integer cardId) {
        return cr.findById(cardId)
                .orElseThrow(() -> new CreditCardException("Card not found"));
    }

    // Delete
    public void deleteByCardId(Integer cardId) {
        cr.deleteByCardId(cardId);
    }

    // By Status
    public List<Creditcards> findByStatus(String status) {
        return cr.findByStatus(status);
    }

    // By Type
    public List<Creditcards> findByCardType(String type) {
        return cr.findByCardType(type);
    }

    // Cards by Customer
    public List<Creditcards> findCardsByCustomer(Integer customerId) {
        return cr.findCardsByCustomerId(customerId);
    }

    // Count
    public Long countCardsByCustomer(Integer customerId) {
        return cr.countCards(customerId);
    }

    // Limit Greater
    public List<Creditcards> findCardsWithLimitGreaterThan(BigDecimal limit) {
        return cr.findCardsWithLimitGreaterThan(limit);
    }

    // Limit Less
    public List<Creditcards> findCardsWithLimitLessThan(BigDecimal limit) {
        return cr.findCardsWithLimitLessThan(limit);
    }

    // Sort
    public List<Creditcards> findAllCardsOrderByLimitDesc() {
        return cr.findAllCardsOrderByLimitDesc();
    }

    // Range
    public List<Creditcards> findCardsByLimitRange(BigDecimal min, BigDecimal max) {
        return cr.findCardsByLimitRange(min, max);
    }

    // Max
    public BigDecimal getMaxLimit(Integer customerId) {
        return cr.maxLimit(customerId);
    }

    // Min
    public BigDecimal getMinLimit(Integer customerId) {
        return cr.minLimit(customerId);
    }

    // Group totals
    public List<Object[]> groupedTotals() {
        return cr.totalLimitGroupedByCustomer();
    }

    // High totals
    public List<Object[]> highTotal(BigDecimal limit) {
        return cr.customersWithHighTotal(limit);
    }

    // Issue date
    public List<CreditCustomized> findCardsByIssueDate(LocalDate issueDate) {
        return cr.findCardsByIssueDate(issueDate);
    }

    // Summary
    public List<CustomerCustomized> getAllCustomersWithCards() {
        return cr.findAllCustomersWithCardsLeftJoin();
    }
}