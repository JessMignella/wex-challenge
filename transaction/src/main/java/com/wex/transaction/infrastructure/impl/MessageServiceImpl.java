package com.wex.transaction.infrastructure.impl;

import com.wex.transaction.domain.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

	private final MessageSource messageSource;

	@Override
	public String getMessage(String code, Object[] args, Locale locale) {
		return messageSource.getMessage(code, args, locale);
	}

	@Override
	public String getMessage(String code, Object[] args) {
		return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());

	}

	@Override
	public String getMessage(String code) {
		return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
	}

}
