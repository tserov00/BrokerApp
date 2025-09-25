package org.example.brokerapplication.repository;

import org.example.brokerapplication.entity.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Integer> {
    Optional<SavingsAccount> findByCurrency_IdAndAccount_Id(Long currencyId, Long accountId);
    List<SavingsAccount> findAllByAccount_Id(Long customerAccountId);
}
