package org.example.brokerapplication.dto.response;

import java.math.BigDecimal;

public class PortfolioResponseDto {
    private Long securityId;
    private String ticker;
    private Integer totalQuantity;
    private BigDecimal avgBuyPrice;
    private BigDecimal currentPrice;
    private String currencyCode;
    private BigDecimal unrealizedProfit;

    public PortfolioResponseDto() {}

    public PortfolioResponseDto(Long securityId, String ticker, Integer totalQuantity, BigDecimal avgBuyPrice, BigDecimal currentPrice, String currencyCode, BigDecimal unrealizedProfit) {
        this.securityId = securityId;
        this.ticker = ticker;
        this.totalQuantity = totalQuantity;
        this.avgBuyPrice = avgBuyPrice;
        this.currentPrice = currentPrice;
        this.currencyCode = currencyCode;
        this.unrealizedProfit = unrealizedProfit;
    }

    public Long getSecurityId() {
        return securityId;
    }

    public void setSecurityId(Long securityId) {
        this.securityId = securityId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getAvgBuyPrice() {
        return avgBuyPrice;
    }

    public void setAvgBuyPrice(BigDecimal avgBuyPrice) {
        this.avgBuyPrice = avgBuyPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getUnrealizedProfit() {
        return unrealizedProfit;
    }

    public void setUnrealizedProfit(BigDecimal unrealizedProfit) {
        this.unrealizedProfit = unrealizedProfit;
    }
}
