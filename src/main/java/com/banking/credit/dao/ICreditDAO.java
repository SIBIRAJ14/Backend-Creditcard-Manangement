package com.banking.credit.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.banking.credit.entity.Creditcards;
@Repository
public interface ICreditDAO extends JpaRepository<Creditcards,Integer>{
	@Query(name = "Creditcards.findByStatus")
    List<Creditcards> findByStatus(@Param("status") String status);

    @Query(name = "Creditcards.findByCardType")
    List<Creditcards> findByCardType(@Param("cardType") String cardType);

	@Query("SELECT c FROM Creditcards c WHERE c.customerId.customerId = :customerId")
	List<Creditcards> findCardsByCustomerId(@Param("customerId") Integer customerId);

  
    @Modifying
    @Transactional
    @Query("UPDATE Creditcards c SET c.status = :status WHERE c.cardId = :cardId")
    int updateCardStatus(@Param("cardId") Integer cardId, @Param("status") String status);


    @Modifying
    @Transactional
    @Query("DELETE FROM Creditcards c WHERE c.cardId = :cardId")
    void deleteByCardId(@Param("cardId") Integer cardId);
    //count cards by customer
    @Query("SELECT COUNT(c) FROM Creditcards c WHERE c.customerId.customerId = :customerId")
    Long countCards(@Param("customerId") Integer customerId);
    
    @Query(name = "Creditcards.findAllCardsOrderByLimitDesc")
    List<Creditcards> findAllCardsOrderByLimitDesc();
    
    @Query("SELECT c FROM Creditcards c WHERE c.creditLimit > :limit")
    List<Creditcards> findCardsWithLimitGreaterThan(@Param("limit") BigDecimal limit);
    
    @Query("SELECT c FROM Creditcards c WHERE c.creditLimit < :limit")
    List<Creditcards> findCardsWithLimitLessThan(@Param("limit") BigDecimal limit);
    
    @Query("SELECT c FROM Creditcards c WHERE c.creditLimit BETWEEN :min AND :max")
    List<Creditcards> findCardsByLimitRange(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
    
    @Query("SELECT MAX(c.creditLimit) FROM Creditcards c WHERE c.customerId.customerId = :customerId")
    BigDecimal maxLimit(@Param("customerId") Integer customerId);

    @Query("SELECT MIN(c.creditLimit) FROM Creditcards c WHERE c.customerId.customerId = :customerId")
    BigDecimal minLimit(@Param("customerId") Integer customerId);

    @Query("SELECT c.customerId.customerId, SUM(c.creditLimit) " + "FROM Creditcards c GROUP BY c.customerId.customerId")
    List<Object[]> totalLimitGroupedByCustomer();
    
    @Query("SELECT c.customerId.customerId, SUM(c.creditLimit) " + "FROM Creditcards c GROUP BY c.customerId.customerId HAVING SUM(c.creditLimit) > :limit")
    List<Object[]> customersWithHighTotal(@Param("limit") BigDecimal limit);
    
    @Query("SELECT c.cardId AS cardId, c.customerId.customerId AS customerId, c.status AS status, c.cardType AS cardType, c.creditLimit AS creditLimit, c.issueDate AS issueDate " + "FROM Creditcards c WHERE c.issueDate = :issueDate")
    List<CreditCustomized> findCardsByIssueDate(@Param("issueDate") LocalDate issueDate);
    
    @Query("SELECT cust.customerId AS customerId, cust.name AS name, cust.email AS email, cust.phone AS phone, " +
    	       "c.cardId AS cardId, c.cardType AS cardType, c.creditLimit AS creditLimit, c.status AS status " +
    	       "FROM Creditcards c LEFT JOIN c.customerId cust")
    List<CustomerCustomized> findAllCustomersWithCardsLeftJoin();
    
    @Query("""
    		SELECT
    		 c.cardId as cardId,
    		 c.cardType as cardType,
    		 c.status as status,
    		 c.issueDate as issueDate,
    		 c.creditLimit as creditLimit,
    		 c.customerId.customerId as customerId
    		FROM Creditcards c
    		""")
    		List<CreditCustomized> fetchAllCustomized();

	boolean existsByCustomerId_CustomerId(Integer id);
}
