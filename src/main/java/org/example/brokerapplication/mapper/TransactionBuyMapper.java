package org.example.brokerapplication.mapper;

import org.example.brokerapplication.dto.response.TransactionResponseDto;
import org.example.brokerapplication.entity.Transaction;
import org.example.brokerapplication.enumeration.OrderTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionBuyMapper {
    @Mapping(source = "buyOrder.security.ticker", target = "ticker")
    @Mapping(source = "buyOrder.orderType.type", target = "transactionType")
    @Mapping(source = "buyOrder.id", target = "orderId")
    TransactionResponseDto toResponseDto(Transaction transaction);

    default String map(OrderTypeEnum orderTypeEnum) {
        return orderTypeEnum.getDescription();
    }
}
