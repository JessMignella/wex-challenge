package com.wex.transaction.infrastructure.exception;

import lombok.Getter;
@Getter
public class TransactionNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -8544319619499462682L;
    private String description;

    public  TransactionNotFoundException(String message, String description){
        super(message);
        this.description =description;
    }
}
