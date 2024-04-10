package com.wex.transaction.domain.model;

public enum TransactionType {
    PURCHASE("Purchase"),
    REFUND("Refund"),
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal"),
    TRANSFER("Transfer"),
    PAYMENT("Payment"),
    FEE("Fee"),
    INTEREST("Interest"),
    DIVIDEND("Dividend"),
    OTHER("Other");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }

}
