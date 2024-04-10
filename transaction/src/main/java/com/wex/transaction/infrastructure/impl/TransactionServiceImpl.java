package com.wex.transaction.infrastructure.impl;

import com.wex.transaction.infrastructure.exception.TransactionNotFoundException;
import com.wex.transaction.domain.model.Transaction;
import com.wex.transaction.domain.service.TransactionService;
import com.wex.transaction.infrastructure.repository.TransactionRepository;
import com.wex.transaction.domain.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
private final TransactionRepository transactionRepository;
private final MessageService messageService;


    @Override
    public Transaction find(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException(
                        this.messageService.getMessage("transaction.exceptions.transaction-dont-exists-message"),
                        this.messageService.getMessage("transaction.exceptions.transaction-dont-exists-description")));
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

}
