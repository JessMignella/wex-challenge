package com.wex.transaction.domain.service;

import com.wex.transaction.domain.model.ExchangeRate;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExchangeRateService {

    ExchangeRate find(String currency, String country, LocalDate recordDate);
}
