package org.example.brokerapplication.mapper;

import org.example.brokerapplication.dto.response.SavingsAccountResponseDto;
import org.example.brokerapplication.entity.Currency;
import org.example.brokerapplication.entity.SavingsAccount;
import org.example.brokerapplication.enumeration.CurrencyCodeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SavingsAccountMapper {
    @Mapping(source = "currency.code", target = "currencyCode")
    SavingsAccountResponseDto toResponseDto(SavingsAccount savingsAccount);

    default String map(CurrencyCodeEnum currencyCode) {
        return currencyCode.getCode();
    }
}
