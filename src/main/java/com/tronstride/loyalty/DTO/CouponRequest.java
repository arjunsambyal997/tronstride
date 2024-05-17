package com.tronstride.loyalty.DTO;


import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class CouponRequest {

    private String codeType;
    private int codeCount;
    private int codeLength;
    private String prefix;
    private String postfix;
    private String pattern;
    private String customCode;

}



