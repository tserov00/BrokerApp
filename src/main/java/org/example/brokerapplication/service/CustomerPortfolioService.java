package org.example.brokerapplication.service;

import org.example.brokerapplication.dto.response.PortfolioResponseDto;
import org.example.brokerapplication.entity.CustomerPortfolio;
import org.example.brokerapplication.mapper.CustomerPortfolioMapper;
import org.example.brokerapplication.repository.CustomerPortfolioRepository;
import org.example.brokerapplication.security.CustomerAccountDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerPortfolioService {
    private final CustomerPortfolioRepository portfolioRepository;
    private final CustomerPortfolioMapper portfolioMapper;

    public CustomerPortfolioService(CustomerPortfolioRepository portfolioRepository, CustomerPortfolioMapper portfolioMapper) {
        this.portfolioRepository = portfolioRepository;
        this.portfolioMapper = portfolioMapper;
    }

    public List<PortfolioResponseDto> getPortfolio(CustomerAccountDetails customerAccountDetails) {
        List<CustomerPortfolio> portfolio = portfolioRepository.findAllByCustomerAccount_Id(customerAccountDetails.getAccount().getId());
        List<PortfolioResponseDto> responseList = new ArrayList<>();
        for (CustomerPortfolio portfolioItem : portfolio) {
            PortfolioResponseDto responsePortfolio = portfolioMapper.toResponseDto(portfolioItem);
            Integer availableQuantity = portfolioItem.getTotalQuantity();
            if (portfolioItem.getSoldQuantity() != null) {
                availableQuantity = portfolioItem.getTotalQuantity() - portfolioItem.getSoldQuantity();
            }
            BigDecimal profit = portfolioItem.getSecurity().getLastPrice().subtract(portfolioItem.getAvgBuyPrice());
            responsePortfolio.setUnrealizedProfit(profit.multiply(BigDecimal.valueOf(availableQuantity)));
            responseList.add(responsePortfolio);
        }
        return responseList;
    }
}
