package com.wex.transaction.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ExchangeRateRequest {
 private String country;
 private String currency;
 @JsonProperty("country_currency_desc")
 private String countryCurrencyDesc;
 @JsonProperty("exchange_rate")
 private BigDecimal rate;
 @JsonProperty("record_date")
 private LocalDate recordDate;
}
