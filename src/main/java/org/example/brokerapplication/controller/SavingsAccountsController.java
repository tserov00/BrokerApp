package org.example.brokerapplication.controller;

import jakarta.validation.Valid;
import org.example.brokerapplication.dto.response.SavingsAccountHistoryResponseDto;
import org.example.brokerapplication.dto.request.SavingsAccountTransactionDto;
import org.example.brokerapplication.dto.response.SavingsAccountResponseDto;
import org.example.brokerapplication.security.CustomerAccountDetails;
import org.example.brokerapplication.service.SavingsAccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class SavingsAccountsController {

    private final SavingsAccountsService savingsAccountsService;

    public SavingsAccountsController(SavingsAccountsService savingsAccountsService) {
        this.savingsAccountsService = savingsAccountsService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<SavingsAccountResponseDto>> getSavingsAccounts(@AuthenticationPrincipal CustomerAccountDetails customerAccountDetails) {
        List savingsAccounts = savingsAccountsService.getSavingsAccounts(customerAccountDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>(savingsAccounts));
    }

    @GetMapping("/history")
    public ResponseEntity<List<SavingsAccountHistoryResponseDto>> getHistory(@AuthenticationPrincipal CustomerAccountDetails customerAccountDetails) {
        List history = savingsAccountsService.getHistory(customerAccountDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>(history));
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(@Valid @RequestBody SavingsAccountTransactionDto transactionDto, @AuthenticationPrincipal CustomerAccountDetails customerAccountDetails) {
        savingsAccountsService.deposit(customerAccountDetails, transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@Valid @RequestBody SavingsAccountTransactionDto transactionDto, @AuthenticationPrincipal CustomerAccountDetails customerAccountDetails) {
        savingsAccountsService.withdraw(customerAccountDetails, transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
