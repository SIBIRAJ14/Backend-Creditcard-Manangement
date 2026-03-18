package com.banking.credit;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.banking.credit.bo.CreditBO;
import com.banking.credit.bo.CustomerBO;
import com.banking.credit.entity.City;
import com.banking.credit.entity.Country;
import com.banking.credit.entity.Creditcards;
import com.banking.credit.entity.Customers;
import com.banking.credit.entity.State;
import com.banking.credit.exception.GlobalExceptionHandler;
import com.banking.credit.service.CreditService;

import jdk.internal.org.jline.terminal.TerminalBuilder.SystemOutput;

@SpringBootApplication
@EnableJpaAuditing
public class CreditAppApplication {

    private final GlobalExceptionHandler globalExceptionHandler;

    CreditAppApplication(GlobalExceptionHandler globalExceptionHandler) {
        this.globalExceptionHandler = globalExceptionHandler;
    }

	public static void main(String[] args) {
		ApplicationContext ctx =SpringApplication.run(CreditAppApplication.class, args);
		System.out.println("welcome to springboot");
		/*
		 * CreditService credit = ctx.getBean(CreditService.class); credit.printMsg();
		 */
		
		
		CreditBO bo1 = ctx.getBean(CreditBO.class);
		CustomerBO bo = ctx.getBean(CustomerBO.class);
		

		/*
		 * Creditcards c = new Creditcards(); Customers cc = new Customers();
		 * c.setCardId(2); cc.setCustomerId(1); c.setCardType("dimond");
		 * c.setCustomerId(cc); c.setStatus("approved");
		 * c.setIssueDate(LocalDate.parse("2025-09-05"));
		 * c.setCreditLimit(BigDecimal.valueOf(20000));
		 * 
		 * bo.insert(c);
		 */
		
		//-------------------------------------------------------------------------
		
		/*
		 * Creditcards c = new Creditcards(); c.setCardType("silver");
		 * c.setCreditLimit(BigDecimal.valueOf(38000));
		 * c.setIssueDate(LocalDate.parse("2025-03-24")); c.setStatus("active");
		 */

		/*
		 * Creditcards cc = new Creditcards(); cc.setCardType("platinum");
		 * cc.setCreditLimit(BigDecimal.valueOf(16000));
		 * cc.setIssueDate(LocalDate.parse("2025-03-13")); cc.setStatus("active");
		 */

		/*
		 * Customers cs = new Customers(); City vm = new City(); vm.setCityId(101);
		 * Country cm = new Country(); cm.setCountryId(1);
		 */
		/*
		 * State sm = new State(); sm.setStateId(11); cs.setCityId(vm);
		 * cs.setCountryId(cm); cs.setStateId(sm); // cs.setCustomerId(4);
		 * cs.setEmail("Ram@gmail.com"); cs.setName("Ram"); cs.setPhone(9876808534l);
		 * cs.setDateOfBirth(LocalDate.parse("2004-05-15"));
		 */
		// ----
		/*
		 * c.setCustomerId(cs); cc.setCustomerId(cs);
		 */
		// -----
		/*
		 * List<Creditcards> list = new ArrayList<Creditcards>(); list.add(c);
		 * list.add(cc);
		 */

		/*
		 * cs.setCreditcards(list); // -------- bo.insert(cs);
		 */
		
		
		/*
		 * Customers cc = bo.findCustomer(4); System.out.println("Customer" + cc);
		 * List<Creditcards> crList = cc.getCreditcards(); for (Creditcards crObj :
		 * crList) { System.out.println("Credit list  " + crObj.getCardId());
		 * 
		 * }
		 */
		// System.out.println(bo.findCard(1));
		//-------------------------------------------------------------------
		CreditService crs = ctx.getBean(CreditService.class);
		
		
		/*
		 * Creditcards cr1 = new Creditcards(); cr1.setCardType("gold");
		 * cr1.setCreditLimit(BigDecimal.valueOf(40000));
		 * cr1.setIssueDate(LocalDate.parse("2022-06-30")); cr1.setStatus("active");
		 */

		/*
		 * Customers cs1 = new Customers(); cs1.setCustomerId(4);
		 * cr1.setCustomerId(cs1);
		 */
		/*
		 * Customers cs2 = new Customers(); City q1 = new City(); q1.setCityId(101);
		 * State w1 = new State(); w1.setStateId(11); Country e1 = new Country();
		 * e1.setCountryId(1); cs2.setCityId(q1); cs2.setCountryId(e1);
		 * cs2.setDateOfBirth(LocalDate.parse("2003-02-01"));
		 * cs2.setEmail("vin@gmail.com"); cs2.setName("vin"); cs2.setPhone(9090223456l);
		 * cs2.setStateId(w1);
		 */

		/* crs.transactionCard(cs2, cr1); */
//________________________________________________________________________________________________________________________________________
		
		//bo1.deleteByCardId(23);
		//List<Creditcards> cards = bo1.findCardsByCustomer(1);
		//System.out.println(cards);
		//System.out.println(bo1.countCardsByCustomer(1));
		
		CreditService se = ctx.getBean(CreditService.class);
		//System.out.println(se.findCard(24));
		
		//System.out.println(se.findCardsWithLimitGreaterThan(BigDecimal.valueOf(40000)));
		//System.out.println(se.findByStatus("active"));
		
		
		
		
		
	
	}

}
