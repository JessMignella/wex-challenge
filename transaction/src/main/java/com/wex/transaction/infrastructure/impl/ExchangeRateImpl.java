package com.wex.transaction.infrastructure.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wex.transaction.application.dto.request.ExchangeRateRequest;
import com.wex.transaction.application.dto.response.ExchangeRateResponse;
import com.wex.transaction.application.mapper.ExchangeRateMapper;
import com.wex.transaction.domain.model.ExchangeRate;
import com.wex.transaction.domain.service.ExchangeRateService;
import com.wex.transaction.domain.service.MessageService;
import com.wex.transaction.infrastructure.exception.ExchangeRateFetchException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExchangeRateImpl implements ExchangeRateService {
    //@Value("${exchange-rate-api.url}")
    private final String apiUrl = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange";

    private final String fixedParams = "?sort=-record_date&fields=country,currency,country_currency_desc,exchange_rate,record_date&";
    private final ExchangeRateMapper mapper;
    private final MessageService messageService;


    @Override
    public ExchangeRate find(String currency, String country, LocalDate recordDate) {
        if (StringUtils.isEmpty(country) && StringUtils.isEmpty(currency)) {
            throw new ExchangeRateFetchException(messageService.getMessage("exchange-rate.exceptions.fetch-error-null-param-message"),
                    messageService.getMessage("exchange-rate.exceptions.fetch-error-null-param-description"));
        }

        String url = buildUrl(currency, country, recordDate);
        ResponseEntity<Map> response = executeRequest(url);

        if (response.getStatusCode() == HttpStatus.OK && !response.getBody().isEmpty()) {
            return getExchangeRates(response.getBody()).get(0);
        } else {
            throw new ExchangeRateFetchException(messageService.getMessage("exchange-rate.exceptions.fetch-error-message"),
                    messageService.getMessage("exchange-rate.exceptions.fetch-error-description") + " " + response.getStatusCode());
        }
    }

    private String buildUrl(String currency, String country, LocalDate recordDate) {
        return UriComponentsBuilder.fromUriString(apiUrl + fixedParams)
                .queryParam("filter",
                        "record_date:lte:" + recordDate
                                + ",record_date:gte:" + recordDate.minusMonths(6)
                                + (StringUtils.hasText(country) ? ",country:in:" + StringUtils.capitalize(country) : "")
                                + (StringUtils.hasText(currency) ? ",currency:eq:" + StringUtils.capitalize(currency) : ""))
                .toUriString();
    }

    private ResponseEntity<Map> executeRequest(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(null, headers), Map.class);
    }

    private List<ExchangeRate> getExchangeRates(Map result) {
        List<Map> exchangeRates = (List<Map>) result.get("data");
        return exchangeRates.stream().map(item -> ExchangeRate.builder()
                .country((String) item.get("country"))
                .currency((String) item.get("currency"))
                .countryCurrencyDesc((String) item.get("country_currency_desc"))
                .rate(new BigDecimal((String) item.get("exchange_rate")))
                .recordDate(LocalDate.parse((String) item.get("record_date")))
                .build()).toList();
    }

}
