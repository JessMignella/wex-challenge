package com.wex.transaction.domain.service;

import org.springframework.lang.Nullable;

import java.util.Locale;

public interface MessageService {

	String getMessage(String code, @Nullable Object[] args, Locale locale);

	String getMessage(String code, @Nullable Object[] args);

	String getMessage(String code);

}
