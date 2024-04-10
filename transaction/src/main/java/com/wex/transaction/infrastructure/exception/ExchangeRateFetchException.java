package com.wex.transaction.infrastructure.exception;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public class ExchangeRateFetchException extends RuntimeException {
    private static final long serialVersionUID = 994767012116018854L;
    private String description;

    public  ExchangeRateFetchException(String message, String description){
        super(message);
        this.description =description;
    }
}
