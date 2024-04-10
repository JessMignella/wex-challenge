package com.wex.transaction.application.mapper;

import com.wex.transaction.application.dto.request.TransactionRequest;
import com.wex.transaction.application.dto.response.TransactionResponse;
import com.wex.transaction.domain.model.Transaction;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TransactionMapper {


    @Mapping(target = "originalAmount",source = "amount")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TransactionResponse toResponse(Transaction transaction);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Transaction toEntity(TransactionRequest request);

}
