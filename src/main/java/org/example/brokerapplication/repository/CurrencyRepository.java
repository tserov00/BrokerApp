package org.example.brokerapplication.repository;

import org.example.brokerapplication.entity.Currency;
import org.example.brokerapplication.enumeration.CurrencyCodeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findByCode(CurrencyCodeEnum code);
}
