package org.example.brokerapplication.service;

import org.example.brokerapplication.dto.response.TransactionResponseDto;
import org.example.brokerapplication.entity.Transaction;
import org.example.brokerapplication.mapper.TransactionBuyMapper;
import org.example.brokerapplication.mapper.TransactionSellMapper;
import org.example.brokerapplication.repository.TransactionRepository;
import org.example.brokerapplication.security.CustomerAccountDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionBuyMapper transactionBuyMapper;
    private final TransactionSellMapper transactionSellMapper;

    public TransactionService(TransactionRepository transactionRepository, TransactionBuyMapper transactionBuyMapper, TransactionSellMapper transactionSellMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionBuyMapper = transactionBuyMapper;
        this.transactionSellMapper = transactionSellMapper;
    }

    public List<TransactionResponseDto> getAllTransactions(CustomerAccountDetails customerAccountDetails) {
        List<Transaction> buyTransactions = transactionRepository.findAllByBuyOrder_CustomerAccount_Id(customerAccountDetails.getAccount().getId());
        List<Transaction> sellTransactions = transactionRepository.findAllBySellOrder_CustomerAccount_Id(customerAccountDetails.getAccount().getId());
        List<TransactionResponseDto> responseList = new ArrayList<>();
        for (Transaction buyTransaction : buyTransactions) {
            responseList.add(transactionBuyMapper.toResponseDto(buyTransaction));
        }
        for (Transaction sellTransaction : sellTransactions) {
            responseList.add(transactionSellMapper.toResponseDto(sellTransaction));
        }
        return responseList;
    }
}
