package org.example.brokerapplication.mapper;

import org.example.brokerapplication.dto.response.OrderResponseDto;
import org.example.brokerapplication.entity.Order;
import org.example.brokerapplication.enumeration.OrderStatusEnum;
import org.example.brokerapplication.enumeration.OrderTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "security.ticker", target = "ticker")
    @Mapping(source = "orderType.type", target = "orderType")
    @Mapping(source = "security.lastPrice", target = "currentPrice")
    @Mapping(source = "orderStatus.status", target = "orderStatus")
    @Mapping(source = "availableQuantity", target = "availableQuantity", defaultExpression = "java(0)")
    OrderResponseDto toOrderResponseDto(Order order);

    default String map(OrderTypeEnum orderTypeEnum) {
        return orderTypeEnum.getDescription();
    }

    default String map(OrderStatusEnum orderStatusEnum) {
        return orderStatusEnum.getDescription();
    }
}
