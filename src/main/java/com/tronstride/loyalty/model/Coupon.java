package com.tronstride.loyalty.model;

import jakarta.persistence.Entity;
import lombok.Data;
@Entity
@Data

public class Coupon {
        private String codeType;
        private int codeCount;
        private int codeLength;
        private String prefix;
        private String postfix;
        private String pattern;
        private String customCode;

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

