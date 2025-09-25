package org.example.brokerapplication.repository;

import org.example.brokerapplication.entity.SecurityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityTypeRepository extends JpaRepository<SecurityType, Long> {
}
