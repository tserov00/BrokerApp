package org.example.brokerapplication.mapper;

import org.example.brokerapplication.dto.response.SavingsAccountHistoryResponseDto;
import org.example.brokerapplication.entity.BalanceHistory;
import org.example.brokerapplication.enumeration.CurrencyCodeEnum;
import org.example.brokerapplication.enumeration.TransactionTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BalanceHistoryMapper {
    @Mapping(source = "savingsAccount.currency.code", target = "currencyCode")
    @Mapping(source = "transactionType.transactionType", target = "transactionType")
    SavingsAccountHistoryResponseDto toResponseDto(BalanceHistory balanceHistory);

    default String map(CurrencyCodeEnum currencyCodeEnum) {
        return currencyCodeEnum.getCode();
    }

    default String map(TransactionTypeEnum transactionTypeEnum) {
        return transactionTypeEnum.getDescription();
    }
}
