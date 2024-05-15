package com.tronstride.loyalty.util;


import jakarta.persistence.Entity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Entity
@Data



public class CouponGenerationUtil {
    public List<String> generateCoupons() {
        List<String> coupons = new ArrayList<>();

        for (int i = 0; i < codeCount; i++) {
            String generatedCode = generateCouponCode();
            coupons.add(generatedCode);
        }

        return coupons;
    }

    private String generateCouponCode() {
        StringBuilder couponCode = new StringBuilder();

        // Append prefix
        if (prefix != null) {
            couponCode.append(prefix);
        }

        // Append custom code (if provided)
        if (customCode != null && !customCode.isEmpty()) {
            couponCode.append(customCode);
        } else {
            // Generate random code based on pattern and length
            Random random = new Random();
            String characters = pattern;
            int charactersLength = characters.length();

            for (int i = 0; i < codeLength; i++) {
                char randomChar = characters.charAt(random.nextInt(charactersLength));
                couponCode.append(randomChar);
            }
        }

        // Append postfix
        if (postfix != null) {
            couponCode.append(postfix);
        }

        return couponCode.toString();
    }

}
