package com.tronstride.loyalty.enums;

import lombok.Getter;

@Getter
public enum DiscountType {

    PRODUCT_DISCOUNT("ProductDiscount"),
    ORDER_DISCOUNT("OrderDiscount");

    private final String getName;


    DiscountType(String getName) {
        this.getName = getName;
    }
}
