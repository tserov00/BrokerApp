package org.example.brokerapplication.mapper;

import org.example.brokerapplication.dto.response.MarketSecuritiesResponseDto;
import org.example.brokerapplication.entity.Currency;
import org.example.brokerapplication.entity.Security;
import org.example.brokerapplication.entity.SecurityType;
import org.example.brokerapplication.entity.StockExchange;
import org.example.brokerapplication.enumeration.CurrencyCodeEnum;
import org.example.brokerapplication.enumeration.SecurityTypeEnum;
import org.example.brokerapplication.enumeration.StockExchangeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface MarketMapper {
    @Mapping(source = "security.id", target = "id")
    @Mapping(source = "currency.code", target = "currencyCode")
    @Mapping(source = "securityType.securityType", target = "securityType")
    @Mapping(source = "stockExchange.stockExchangeName", target = "stockExchangeName")
    MarketSecuritiesResponseDto toResponseDto(Security security);

    default String map(SecurityTypeEnum securityTypeEnum) {
        return securityTypeEnum.getDescription();
    }

    default String map(CurrencyCodeEnum currencyCodeEnum) {
        return currencyCodeEnum.getCode();
    }

    default String map(StockExchangeEnum stockExchangeEnum) {
        return stockExchangeEnum.getDescription();
    }
}
