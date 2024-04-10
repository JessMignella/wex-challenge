package com.wex.transaction.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Builder
public class ExchangeRate {
    private String country;
    private String currency;
    private String countryCurrencyDesc;
    private BigDecimal rate;
    private LocalDate recordDate;


    public BigDecimal convertCurrency(BigDecimal originalAmount){
        return originalAmount.multiply(this.rate);
    }
}
