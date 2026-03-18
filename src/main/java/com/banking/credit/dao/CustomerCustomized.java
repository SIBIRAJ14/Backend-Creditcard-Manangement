package com.banking.credit.dao;

import java.math.BigDecimal;

public interface CustomerCustomized {
    Integer getCustomerId();
    String getName();
    String getEmail();
    Long getPhone();
    Integer getCardId();
    String getCardType();
    BigDecimal getCreditLimit();
    String getStatus();
}
