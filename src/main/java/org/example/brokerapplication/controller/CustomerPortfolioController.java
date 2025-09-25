package org.example.brokerapplication.controller;

import org.example.brokerapplication.dto.response.PortfolioResponseDto;
import org.example.brokerapplication.entity.Customer;
import org.example.brokerapplication.security.CustomerAccountDetails;
import org.example.brokerapplication.service.CustomerPortfolioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class CustomerPortfolioController {
    private final CustomerPortfolioService customerPortfolioService;

    public CustomerPortfolioController(CustomerPortfolioService customerPortfolioService) {
        this.customerPortfolioService = customerPortfolioService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<PortfolioResponseDto>> getPortfolio(@AuthenticationPrincipal CustomerAccountDetails accountDetails) {
        List<PortfolioResponseDto> responseList = customerPortfolioService.getPortfolio(accountDetails);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }
}
