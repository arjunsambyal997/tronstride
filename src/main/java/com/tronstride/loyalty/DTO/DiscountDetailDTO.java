package com.tronstride.loyalty.DTO;

import com.tronstride.loyalty.model.DiscountTypeEntity;
import com.tronstride.loyalty.model.OrderDiscountTypeEntity;
import com.tronstride.loyalty.model.ProductDiscountTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDetailDTO {

    private DiscountTypeEntity discountType;

    private OrderDiscountTypeEntity orderDiscountType;

    private ProductDiscountTypeEntity productDiscountType;

}
