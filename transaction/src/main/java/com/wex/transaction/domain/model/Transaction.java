package com.wex.transaction.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate transactionDate;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private  TransactionType transactionType;

}
