package org.example.brokerapplication.repository;

import org.example.brokerapplication.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findAllByCustomerAccount_Id(Long customerId);
}
