package com.tronstride.loyalty.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Coupon {
    @jakarta.persistence.Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name = "id_generator", sequenceName = "id_seq")//type
    private Integer Id;
    public String codeType;
    public int codeCount;
    public int codeLength;
    public String prefix;
    public String postfix;
    public String pattern;
    public String customCode;

    public Coupon(String codeType, int codeCount, int codeLength, String prefix, String postfix, String pattern, String customCode) {
        this.codeType = codeType;
        this.codeCount = codeCount;
        this.codeLength = codeLength;
        this.prefix = prefix;
        this.postfix = postfix;
        this.pattern = pattern;
        this.customCode = customCode;
    }


}


