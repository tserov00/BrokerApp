package org.example.brokerapplication.controller;

import org.example.brokerapplication.dto.response.TransactionResponseDto;
import org.example.brokerapplication.entity.Transaction;
import org.example.brokerapplication.security.CustomerAccountDetails;
import org.example.brokerapplication.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(("/api/transactions"))
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/history")
    public ResponseEntity<List<TransactionResponseDto>> getTransactions(@AuthenticationPrincipal CustomerAccountDetails customerAccountDetails) {
        List<TransactionResponseDto> responseList = transactionService.getAllTransactions(customerAccountDetails);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }
}
