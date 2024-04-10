package com.wex.transaction.application.dto.request;

import com.wex.transaction.domain.model.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
@Data
public class TransactionRequest {
    @Size(max=50, message = "Description must not exceed 50 characters")
    private String description;
    @NotNull(message = "transactionDate can not be null")
    @PastOrPresent(message = "The date and time must be in the past or present")
    private LocalDate transactionDate;
    @NotNull(message = "amount can not be null")
    @Positive(message = "amount must be positive and greater than zero")
    private BigDecimal amount;
    @NotNull(message = "transactionType can not be null")
    private TransactionType transactionType;
    public static BigDecimal roundAmount(BigDecimal valor) {
        if (valor == null) {
            return null;
        }
        return valor.setScale(2, RoundingMode.HALF_UP);
    }
}
