package org.example.brokerapplication.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.brokerapplication.dto.request.OrderDto;
import org.example.brokerapplication.dto.response.OrderResponseDto;
import org.example.brokerapplication.entity.*;
import org.example.brokerapplication.exception.custom.InsufficientBalanceException;
import org.example.brokerapplication.exception.custom.InsufficientSecurityException;
import org.example.brokerapplication.exception.custom.MissingCurrencyException;
import org.example.brokerapplication.mapper.OrderMapper;
import org.example.brokerapplication.repository.*;
import org.example.brokerapplication.security.CustomerAccountDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @PersistenceContext
    private final EntityManager entityManager;

    private final OrderRepository orderRepository;
    private final SavingsAccountRepository savingsAccountRepository;
    private final SecurityRepository securityRepository;
    private final CustomerPortfolioRepository portfolioRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, SavingsAccountRepository savingsAccountRepository, SecurityRepository securityRepository, CustomerPortfolioRepository portfolioRepository, OrderMapper orderMapper, EntityManager entityManager) {
        this.orderRepository = orderRepository;
        this.savingsAccountRepository = savingsAccountRepository;
        this.securityRepository = securityRepository;
        this.portfolioRepository = portfolioRepository;
        this.orderMapper = orderMapper;
        this.entityManager = entityManager;
    }

    @Transactional
    public void createBuyOrder(OrderDto buyOrder, CustomerAccountDetails customerAccountDetails) {
        CustomerAccount account = customerAccountDetails.getAccount();
        Security security = securityRepository.findById(buyOrder.getSecurityId()).orElseThrow(() -> new RuntimeException("Ценная бумага не найдена"));
        Currency securityCurrency = security.getCurrency();
        SavingsAccount savingsAccount = savingsAccountRepository.findByCurrency_IdAndAccount_Id(securityCurrency.getId(), account.getId())
                                                                .orElseThrow(() -> new MissingCurrencyException("У вас нет счета с нужной валютой"));

        BigDecimal fee = (BigDecimal) entityManager.createNativeQuery("SELECT calculate_broker_fee(?1)", BigDecimal.class)
                .setParameter(1, buyOrder.getPrice())
                .getSingleResult();

        BigDecimal totalCost = buyOrder.getPrice()
                .multiply(BigDecimal.valueOf(buyOrder.getQuantity()))
                .add(fee);

        BigDecimal totalReservedAmount = savingsAccount.getReservedAmount().add(totalCost);

        if (totalReservedAmount.compareTo(savingsAccount.getBalance()) > 0) {
            throw new InsufficientBalanceException("Недостаточно средств");
        }

         entityManager.createNativeQuery("CALL create_buy_order(:p_security_id, :p_price, :p_quantity, :p_savings_account_id)")
                .setParameter("p_security_id", security.getId().intValue())
                .setParameter("p_price", buyOrder.getPrice())
                .setParameter("p_quantity", buyOrder.getQuantity())
                .setParameter("p_savings_account_id", savingsAccount.getId().intValue())
                 .executeUpdate();
    }

    @Transactional
    public void createSellOrder(OrderDto sellOrder, CustomerAccountDetails customerAccountDetails) {
        CustomerAccount account = customerAccountDetails.getAccount();
        Security security = securityRepository.findById(sellOrder.getSecurityId()).orElseThrow(() -> new RuntimeException("Ценная бумага не найдена"));
        Currency securityCurrency = security.getCurrency();
        SavingsAccount savingsAccount = savingsAccountRepository.findByCurrency_IdAndAccount_Id(securityCurrency.getId(), account.getId())
                .orElseThrow(() -> new MissingCurrencyException("У вас нет счета с нужной валютой"));

        CustomerPortfolio portfolio = portfolioRepository.findByCustomerAccount_IdAndSecurity_Id(account.getId(), security.getId())
                .orElseThrow(() -> new InsufficientSecurityException("Недостаточно ценных бумаг для прождажи"));

        if (portfolio.getTotalQuantity() - portfolio.getReservedQuantity() < sellOrder.getQuantity()) {
            throw new InsufficientSecurityException("Недостаточно ценных бумаг для продажи");
        }

        entityManager.createNativeQuery("CALL create_sell_order(:p_security_id, :p_price, :p_quantity, :p_savings_account_id)")
                .setParameter("p_security_id", security.getId().intValue())
                .setParameter("p_price", sellOrder.getPrice())
                .setParameter("p_quantity", sellOrder.getQuantity())
                .setParameter("p_savings_account_id", savingsAccount.getId().intValue())
                .executeUpdate();
    }

    public List<OrderResponseDto> getHistory(CustomerAccountDetails customerAccountDetails) {
        List<Order> orders = orderRepository.findAllByCustomerAccount_Id(customerAccountDetails.getAccount().getId());
        List<OrderResponseDto> responseList = new ArrayList<>();
        for (Order order : orders) {
            responseList.add(orderMapper.toOrderResponseDto(order));
        }
        return responseList;
    }
}
