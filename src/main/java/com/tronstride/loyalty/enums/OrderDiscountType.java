package com.tronstride.loyalty.enums;

import lombok.Getter;


@Getter
public enum OrderDiscountType {
    ORDER_DISCOUNT("Order"),
    PERCENTAGE_DISCOUNT("Percentage"),
    NEW_PRICE("NewPrice");

    private final String getType;

    OrderDiscountType(String getType) {
        this.getType = getType;
    }
}
