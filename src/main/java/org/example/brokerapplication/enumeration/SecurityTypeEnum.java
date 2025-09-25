package org.example.brokerapplication.enumeration;

public enum SecurityTypeEnum {
    STOCK("STOCK");

    private String description;

    SecurityTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
