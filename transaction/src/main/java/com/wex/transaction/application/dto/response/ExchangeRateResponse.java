package com.wex.transaction.application.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExchangeRateResponse {
    private String countryCurrencyDesc;
    private BigDecimal rate;
    private LocalDate recordDate;
}
