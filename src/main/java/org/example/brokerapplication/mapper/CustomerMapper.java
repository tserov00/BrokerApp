package org.example.brokerapplication.mapper;

import org.example.brokerapplication.dto.response.CustomerRegistrationResponseDto;
import org.example.brokerapplication.entity.Customer;
import org.example.brokerapplication.entity.CustomerAccount;
import org.example.brokerapplication.entity.SavingsAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(source = "customer.id", target = "id")
    CustomerRegistrationResponseDto toResponseDto(Customer customer, CustomerAccount account, SavingsAccount savingsAccount);
}
