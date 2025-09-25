package org.example.brokerapplication.enumeration;

public enum CurrencyCodeEnum {
    USD("USD"),
    EUR("EUR"),
    GBP("GBP"),
    JPY("JPY"),
    CHF("CHF"),
    RUB("RUB");

    private String code;

    CurrencyCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
