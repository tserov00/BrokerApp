package org.example.brokerapplication.mapper;

import org.example.brokerapplication.dto.response.TransactionResponseDto;
import org.example.brokerapplication.entity.Transaction;
import org.example.brokerapplication.enumeration.OrderTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionSellMapper {
    @Mapping(source = "sellOrder.security.ticker", target = "ticker")
    @Mapping(source = "sellOrder.orderType.type", target = "transactionType")
    @Mapping(source = "sellOrder.id", target = "orderId")
    TransactionResponseDto toResponseDto(Transaction transaction);

    default String map(OrderTypeEnum orderTypeEnum) {
        return orderTypeEnum.getDescription();
    }
}
