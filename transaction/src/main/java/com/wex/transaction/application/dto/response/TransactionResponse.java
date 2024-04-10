package com.wex.transaction.application.dto.response;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionResponse {
    private Long id;
    private String description;
    private LocalDate transactionDate;
    private BigDecimal amount;
    private BigDecimal originalAmount;
    private ExchangeRateResponse exchangeRateResponse;
    private String transactionType;
}
