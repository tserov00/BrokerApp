package org.example.brokerapplication.repository;

import org.example.brokerapplication.entity.BalanceHistory;
import org.example.brokerapplication.entity.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BalanceHistoryRepository extends JpaRepository<BalanceHistory, Long> {
    List<BalanceHistory> findAllBySavingsAccountIn(List<SavingsAccount> savingsAccounts);
}
