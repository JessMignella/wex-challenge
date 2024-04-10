package com.wex.transaction.application.impl;

import com.wex.transaction.application.TransactionApplication;
import com.wex.transaction.application.dto.request.TransactionRequest;
import com.wex.transaction.application.dto.response.ExchangeRateResponse;
import com.wex.transaction.application.dto.response.TransactionResponse;
import com.wex.transaction.application.mapper.ExchangeRateMapper;
import com.wex.transaction.application.mapper.TransactionMapper;
import com.wex.transaction.domain.model.ExchangeRate;
import com.wex.transaction.domain.model.Transaction;
import com.wex.transaction.domain.service.ExchangeRateService;
import com.wex.transaction.domain.service.TransactionService;
import com.wex.transaction.utils.handler.ArroundAmount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionApplicationImpl implements TransactionApplication {
    private final TransactionService transactionService;
    private final ExchangeRateService exchangeRateService;
    private final TransactionMapper mapper;
    private final ExchangeRateMapper exchangeRateMapper;

    @Override
    public TransactionResponse find(Long transactionId) {
        Transaction transaction = this.transactionService.find(transactionId);
        return mapper.toResponse(transaction);
    }

    @Override
    public TransactionResponse save(TransactionRequest request) {
        Transaction transaction = mapper.toEntity(request);
        transaction.setAmount(ArroundAmount.arroundUp(transaction.getAmount()));
        return mapper.toResponse(this.transactionService.save(transaction));
    }

    @Override
    public TransactionResponse convertToCurrency(Long transactionId, String currency, String country) {
        TransactionResponse response = this.find(transactionId);
        ExchangeRate exchangeRate = exchangeRateService.find(currency, country, response.getTransactionDate());
        response.setAmount(ArroundAmount.arroundUp(exchangeRate.convertCurrency(response.getOriginalAmount())));
        response.setExchangeRateResponse(exchangeRateMapper.toResponse(exchangeRate));

        return response;
    }

}
