package com.wex.transaction.domain.service;

import com.wex.transaction.domain.model.Transaction;

public interface TransactionService {

    Transaction find(Long transactionId);
    Transaction save(Transaction transaction);



}
