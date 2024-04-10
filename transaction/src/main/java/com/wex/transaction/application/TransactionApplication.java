package com.wex.transaction.application;

import com.wex.transaction.application.dto.request.TransactionRequest;
import com.wex.transaction.application.dto.response.TransactionResponse;

public interface TransactionApplication {

  TransactionResponse find(Long transactionId);
  TransactionResponse save(TransactionRequest request);
  TransactionResponse convertToCurrency(Long transactionId, String currency, String country);
}
