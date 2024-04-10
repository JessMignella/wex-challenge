package com.wex.transaction.application.mapper;

import com.wex.transaction.application.dto.response.ExchangeRateResponse;
import com.wex.transaction.domain.model.ExchangeRate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ExchangeRateResponse toResponse(ExchangeRate exchangeRate);

}
