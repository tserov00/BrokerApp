package org.example.brokerapplication.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.brokerapplication.dto.response.SavingsAccountHistoryResponseDto;
import org.example.brokerapplication.dto.request.SavingsAccountTransactionDto;
import org.example.brokerapplication.dto.response.SavingsAccountResponseDto;
import org.example.brokerapplication.entity.BalanceHistory;
import org.example.brokerapplication.entity.Currency;
import org.example.brokerapplication.entity.SavingsAccount;
import org.example.brokerapplication.enumeration.CurrencyCodeEnum;
import org.example.brokerapplication.exception.custom.InsufficientBalanceException;
import org.example.brokerapplication.exception.custom.MissingCurrencyException;
import org.example.brokerapplication.mapper.BalanceHistoryMapper;
import org.example.brokerapplication.mapper.SavingsAccountMapper;
import org.example.brokerapplication.repository.BalanceHistoryRepository;
import org.example.brokerapplication.repository.CurrencyRepository;
import org.example.brokerapplication.repository.SavingsAccountRepository;
import org.example.brokerapplication.security.CustomerAccountDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class SavingsAccountsService {

    @PersistenceContext
    private final EntityManager entityManager;

    private final SavingsAccountRepository savingsAccountRepository;
    private final CurrencyRepository currencyRepository;
    private final BalanceHistoryRepository balanceHistoryRepository;
    private final SavingsAccountMapper savingsAccountMapper;
    private final BalanceHistoryMapper balanceHistoryMapper;

    public SavingsAccountsService(SavingsAccountRepository savingsAccountRepository, CurrencyRepository currencyRepository, BalanceHistoryRepository balanceHistoryRepository, SavingsAccountMapper savingsAccountMapper, BalanceHistoryMapper balanceHistoryMapper, EntityManager entityManager) {
        this.savingsAccountRepository = savingsAccountRepository;
        this.currencyRepository = currencyRepository;
        this.balanceHistoryRepository = balanceHistoryRepository;
        this.savingsAccountMapper = savingsAccountMapper;
        this.balanceHistoryMapper = balanceHistoryMapper;
        this.entityManager = entityManager;
    }

    public List<SavingsAccountResponseDto> getSavingsAccounts(CustomerAccountDetails customerAccountDetails) {
        List<SavingsAccount> savingsAccounts = savingsAccountRepository.findAllByAccount_Id(customerAccountDetails.getAccount().getId());
        List<SavingsAccountResponseDto> savingsAccountResponseDto = new ArrayList<>();
        for (SavingsAccount savingsAccount : savingsAccounts) {
            savingsAccountResponseDto.add(savingsAccountMapper.toResponseDto(savingsAccount));
        }
        return savingsAccountResponseDto;
    }

    public List<SavingsAccountHistoryResponseDto> getHistory(CustomerAccountDetails customerAccountDetails) {
        List<SavingsAccount> savingsAccounts = savingsAccountRepository.findAllByAccount_Id(customerAccountDetails.getAccount().getId());
        List<BalanceHistory> balanceHistory = balanceHistoryRepository.findAllBySavingsAccountIn(savingsAccounts);
        List<SavingsAccountHistoryResponseDto> savingsAccountHistoryResponseDto = new ArrayList<>();
        for (BalanceHistory history : balanceHistory) {
            savingsAccountHistoryResponseDto.add(balanceHistoryMapper.toResponseDto(history));
        }
        return savingsAccountHistoryResponseDto;
    }

    @Transactional
    public void deposit(CustomerAccountDetails customerAccountDetails, SavingsAccountTransactionDto transactionDto) {
        CurrencyCodeEnum currencyEnum;

        try {
             currencyEnum = CurrencyCodeEnum.valueOf(transactionDto.getCurrencyCode().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неверный код валюты");
        }

        Currency currency = currencyRepository.findByCode(currencyEnum).orElseThrow(() -> new RuntimeException("Валюта не найдена в базе данных"));

        entityManager.createNativeQuery("CALL deposit_balance(:p_amount, :p_currency_id, :p_customer_account_id)")
                .setParameter("p_amount", transactionDto.getAmount())
                .setParameter("p_currency_id", currency.getId().intValue())
                .setParameter("p_customer_account_id", customerAccountDetails.getAccount().getId().intValue())
                .executeUpdate();
    }

    @Transactional
    public void withdraw(CustomerAccountDetails customerAccountDetails, SavingsAccountTransactionDto transactionDto) {
        CurrencyCodeEnum currencyEnum;

        try {
            currencyEnum = CurrencyCodeEnum.valueOf(transactionDto.getCurrencyCode().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неверный код валюты");
        }

        Currency currency = currencyRepository.findByCode(currencyEnum).orElseThrow(() -> new RuntimeException("Валюта не найдена в базе данных"));

        SavingsAccount savingsAccount = savingsAccountRepository.findByCurrency_IdAndAccount_Id(currency.getId(), customerAccountDetails.getAccount().getId())
                .orElseThrow(() -> new MissingCurrencyException("У вас нет счета с нужной валютой"));

        BigDecimal availableAmount = savingsAccount.getBalance().subtract(savingsAccount.getReservedAmount());

        if (availableAmount.compareTo(transactionDto.getAmount()) < 0) {
            throw new InsufficientBalanceException("Недостаточно средств");
        }

        entityManager.createNativeQuery("CALL withdraw_balance(:p_amount, :p_currency_id, :p_customer_account_id)")
                .setParameter("p_amount", transactionDto.getAmount())
                .setParameter("p_currency_id", currency.getId().intValue())
                .setParameter("p_customer_account_id", customerAccountDetails.getAccount().getId().intValue())
                .executeUpdate();
    }
}
