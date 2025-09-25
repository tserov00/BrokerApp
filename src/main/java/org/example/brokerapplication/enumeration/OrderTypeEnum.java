package org.example.brokerapplication.enumeration;

public enum OrderTypeEnum {
    BUY("BUY"),
    SELL("SELL");

    private String description;

    OrderTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
