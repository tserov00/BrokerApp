package org.example.brokerapplication.repository;

import org.example.brokerapplication.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("""
        SELECT t FROM Transaction t WHERE t.buyOrder.customerAccount = :customerAccountId
        OR t.sellOrder.customerAccount = :customerAccountId
""")
    List<Transaction> findAllByCustomerId(Long customerAccountId);

    List<Transaction> findAllByBuyOrder_CustomerAccount_Id(Long customerAccountId);
    List<Transaction> findAllBySellOrder_CustomerAccount_Id(Long customerAccountId);
}
