package org.example.brokerapplication.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "customer_portfolios")
@IdClass(CustomerPortfolio.CustomerPortfolioId.class)
public class CustomerPortfolio {

    @Id
    @ManyToOne
    @JoinColumn(name = "customer_account_id")
    private CustomerAccount customerAccount;

    @Id
    @ManyToOne
    @JoinColumn(name = "security_id")
    private Security security;

    @Column(name = "avg_buy_price")
    private BigDecimal avgBuyPrice;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "reserved_quantity")
    private Integer reservedQuantity;

    @Column(name = "avg_sell_price")
    private BigDecimal avgSellPrice;

    @Column(name = "sold_quantity")
    private Integer soldQuantity;

    public static class CustomerPortfolioId implements Serializable {
        private Long customerAccount;
        private Long security;

        public CustomerPortfolioId() {}

        public CustomerPortfolioId(Long customerAccount, Long security) {
            this.customerAccount = customerAccount;
            this.security = security;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CustomerPortfolioId that = (CustomerPortfolioId) o;
            return Objects.equals(customerAccount, that.customerAccount) && Objects.equals(security, that.security);
        }

        @Override
        public int hashCode() {
            return Objects.hash(customerAccount, security);
        }
    }

    public CustomerPortfolio() {}

    public CustomerPortfolio(CustomerAccount customerAccount, Security security, BigDecimal avgBuyPrice, Integer totalQuantity, Integer reservedQuantity, BigDecimal avgSellPrice, Integer soldQuantity) {
        this.customerAccount = customerAccount;
        this.security = security;
        this.avgBuyPrice = avgBuyPrice;
        this.totalQuantity = totalQuantity;
        this.reservedQuantity = reservedQuantity;
        this.avgSellPrice = avgSellPrice;
        this.soldQuantity = soldQuantity;
    }

    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public BigDecimal getAvgBuyPrice() {
        return avgBuyPrice;
    }

    public void setAvgBuyPrice(BigDecimal avgBuyPrice) {
        this.avgBuyPrice = avgBuyPrice;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(Integer reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public BigDecimal getAvgSellPrice() {
        return avgSellPrice;
    }

    public void setAvgSellPrice(BigDecimal avgSellPrice) {
        this.avgSellPrice = avgSellPrice;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }
}
