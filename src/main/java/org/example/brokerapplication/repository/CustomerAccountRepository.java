package org.example.brokerapplication.repository;

import org.example.brokerapplication.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
    public CustomerAccount findByLogin(String login);
}
