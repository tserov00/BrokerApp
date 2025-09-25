package org.example.brokerapplication.repository;

import org.example.brokerapplication.entity.CustomerPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerPortfolioRepository extends JpaRepository<CustomerPortfolio, Long> {
    List<CustomerPortfolio> findAllByCustomerAccount_Id(Long customerId);
    Optional<CustomerPortfolio> findByCustomerAccount_IdAndSecurity_Id(Long customerAccountId, Long securityId);
}
