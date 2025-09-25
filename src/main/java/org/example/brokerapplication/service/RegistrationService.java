package org.example.brokerapplication.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.brokerapplication.dto.request.CustomerRegistrationDto;
import org.example.brokerapplication.dto.response.CustomerRegistrationResponseDto;
import org.example.brokerapplication.entity.Currency;
import org.example.brokerapplication.entity.Customer;
import org.example.brokerapplication.entity.CustomerAccount;
import org.example.brokerapplication.entity.SavingsAccount;
import org.example.brokerapplication.enumeration.CurrencyCodeEnum;
import org.example.brokerapplication.mapper.CustomerMapper;
import org.example.brokerapplication.repository.CurrencyRepository;
import org.example.brokerapplication.repository.CustomerAccountRepository;
import org.example.brokerapplication.repository.CustomerRepository;
import org.example.brokerapplication.repository.SavingsAccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class RegistrationService {

    @PersistenceContext
    private EntityManager entityManager;

    private final CustomerRepository customerRepository;
    private final CustomerAccountRepository accountRepository;
    private final SavingsAccountRepository savingsAccountRepository;
    private final CurrencyRepository currencyRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomerMapper customerMapper;

    public RegistrationService(CustomerRepository customerRepository, CustomerAccountRepository accountRepository,
                               SavingsAccountRepository savingsAccountRepository, CurrencyRepository currencyRepository,
                               BCryptPasswordEncoder passwordEncoder, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.savingsAccountRepository = savingsAccountRepository;
        this.currencyRepository = currencyRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerMapper = customerMapper;
    }

    @Transactional
    public CustomerRegistrationResponseDto registerCustomer(CustomerRegistrationDto customerDto) {
        Customer customer = new Customer(customerDto.getFirstName(), customerDto.getLastName(), customerDto.getBirthDate(),
                                         customerDto.getPassportSeries(), customerDto.getAddress(), customerDto.getTaxId());

        customer = customerRepository.save(customer);
        String encodedPassword = passwordEncoder.encode(customerDto.getPassword());

        CustomerAccount account = new CustomerAccount(customer, customerDto.getPhoneNumber(),
                                                      customerDto.getEmail(), customerDto.getLogin(), encodedPassword);
        accountRepository.save(account);

        String savingsAccountNumber = (String) entityManager.createNativeQuery("SELECT generate_savings_account_number()").getSingleResult();
        Currency usd = currencyRepository.findById(1L).orElseThrow(() -> new RuntimeException("Валюта не найдена"));

        SavingsAccount savingsAccount = new SavingsAccount(account, savingsAccountNumber, usd, BigDecimal.ZERO, BigDecimal.ZERO);

        savingsAccountRepository.save(savingsAccount);

        CustomerRegistrationResponseDto responseDto = customerMapper.toResponseDto(customer, account, savingsAccount);
        return responseDto;
    }
}
