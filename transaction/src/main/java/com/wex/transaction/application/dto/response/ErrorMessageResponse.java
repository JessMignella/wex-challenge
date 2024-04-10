package com.wex.transaction.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ErrorMessageResponse {
    private int statusCode;
    private LocalDate timestamp;
    private String message;
    private String description;

}
