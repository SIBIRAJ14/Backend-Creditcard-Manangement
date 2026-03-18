package com.banking.credit.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.credit.dao.CreditCustomized;
import com.banking.credit.dao.CustomerCustomized;
import com.banking.credit.dto.CreditRequest;
import com.banking.credit.entity.Creditcards;
import com.banking.credit.service.CreditService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/creditcards")
public class CreditController {

    @Autowired
    private CreditService service;

    @GetMapping("/greet")
    public String greetings() {
        return "this works";
    }

    @PostMapping("/test")
    public String test(@RequestBody String body) {
        return "Received: " + body;
    }

    // Add Card
    @PostMapping("/add")
    public ResponseEntity<Creditcards> addCard(@Valid @RequestBody CreditRequest request) {
        Creditcards savedCard = service.insert(request);
        return ResponseEntity.ok(savedCard);
    }

    // Update Status
    @PutMapping("/updateStatus/{cardId}")
    public ResponseEntity<String> updateCardStatus(
            @PathVariable Integer cardId,
            @RequestBody CreditRequest request) {

        int updated = service.updateCardStatus(cardId, request.getStatus());

        if (updated > 0) {
            return ResponseEntity.ok("Card updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Card not found");
        }
    }

    // Get All
    @GetMapping("/all")
    public List<CreditCustomized> getAllCards() {
        return service.fetchAll();
    }

    // Get By ID
    @GetMapping("/{cardId}")
    public Creditcards getCard(@PathVariable Integer cardId) {
        return service.findCard(cardId);
    }

    // Delete
    @DeleteMapping("/{cardId}")
    public String deleteCard(@PathVariable Integer cardId) {
        service.deleteByCardId(cardId);
        return "Card deleted successfully";
    }

 
 // By Status
    @GetMapping("/status/{status}")
    public List<Creditcards> getByStatus(@PathVariable String status) {
        return service.findByStatus(status);
    }

    // By Type
    @GetMapping("/type/{type}")
    public List<Creditcards> getByType(@PathVariable String type) {
        return service.findByCardType(type);
    }

    // Cards by Customer
    @GetMapping("/customer/{customerId}")
    public List<Creditcards> getByCustomer(@PathVariable Integer customerId) {
        return service.findCardsByCustomer(customerId);
    }

    // Count cards
    @GetMapping("/count/{customerId}")
    public Long countCards(@PathVariable Integer customerId) {
        return service.countCardsByCustomer(customerId);
    }

    @GetMapping("/limit/greater/{amount}")
    public List<Creditcards> greaterThan(@PathVariable BigDecimal amount) {
        return service.findCardsWithLimitGreaterThan(amount);
    }

 
    @GetMapping("/limit/less/{amount}")
    public List<Creditcards> lessThan(@PathVariable BigDecimal amount) {
        return service.findCardsWithLimitLessThan(amount);
    }

    @GetMapping("/limit/sort/desc")
    public List<Creditcards> sortDesc() {
        return service.findAllCardsOrderByLimitDesc();
    }

    @GetMapping("/limit/range/{min}/{max}")
    public List<Creditcards> limitRange(
            @PathVariable BigDecimal min,
            @PathVariable BigDecimal max) {
        return service.inRange(min, max);
    }


    @GetMapping("/limit/max/{customerId}")
    public BigDecimal maxLimit(@PathVariable Integer customerId) {
        return service.maxLimit(customerId);
    }

    @GetMapping("/limit/min/{customerId}")
    public BigDecimal minLimit(@PathVariable Integer customerId) {
        return service.minLimit(customerId);
    }

  
    @GetMapping("/group/totals")
    public List<Object[]> groupedTotals() {
        return service.groupedTotals();
    }

  
    @GetMapping("/group/high/{limit}")
    public List<Object[]> highTotals(@PathVariable BigDecimal limit) {
        return service.highTotals(limit);
    }
    
    @GetMapping("/issuedate/{date}")
    public List<CreditCustomized> getByIssueDate(@PathVariable String date) {
        LocalDate issueDate = LocalDate.parse(date);
        return service.getCardsByIssueDate(issueDate);
    }
    
    @GetMapping("/summary")
    public ResponseEntity<List<CustomerCustomized>> getAllCustomersWithCards() {
        List<CustomerCustomized> cards = service.getAllCustomersWithCards();
        return ResponseEntity.ok(cards);
    }
}
