package org.example.brokerapplication.mapper;

import org.example.brokerapplication.dto.response.PortfolioResponseDto;
import org.example.brokerapplication.entity.CustomerPortfolio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerPortfolioMapper {
    @Mapping(source = "security.id", target = "securityId")
    @Mapping(source = "security.ticker", target = "ticker")
    @Mapping(source = "security.lastPrice", target = "currentPrice")
    @Mapping(source = "security.currency.code", target = "currencyCode")
    PortfolioResponseDto toResponseDto(CustomerPortfolio customerPortfolio);
}
