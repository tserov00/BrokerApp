package org.example.brokerapplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;


@Entity
@Table(name = "savings_accounts")
public class SavingsAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "savings_seq")
    @SequenceGenerator(name = "savings_seq", sequenceName = "savings_accounts_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_account_id")
    private CustomerAccount account;

    @Size(max = 30)
    @Column(name = "savings_account_number")
    private String savingsAccountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "reserved_amount")
    private BigDecimal reservedAmount;

    public SavingsAccount() {}

    public SavingsAccount(CustomerAccount account, String savingsAccountNumber, Currency currency, BigDecimal balance, BigDecimal reservedAmount) {
        this.account = account;
        this.savingsAccountNumber = savingsAccountNumber;
        this.currency = currency;
        this.balance = balance;
        this.reservedAmount = reservedAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerAccount getAccount() {
        return account;
    }

    public void setAccount(CustomerAccount account) {
        this.account = account;
    }

    public String getSavingsAccountNumber() {
        return savingsAccountNumber;
    }

    public void setSavingsAccountNumber(String savingsAccountNumber) {
        this.savingsAccountNumber = savingsAccountNumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getReservedAmount() {
        return reservedAmount;
    }

    public void setReservedAmount(BigDecimal reservedAmount) {
        this.reservedAmount = reservedAmount;
    }
}
