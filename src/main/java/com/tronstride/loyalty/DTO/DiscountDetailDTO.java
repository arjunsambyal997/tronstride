package com.tronstride.loyalty.DTO;

import com.tronstride.loyalty.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDetailDTO {

    private DiscountTypeEntity discountType;

    private OrderDiscountTypeEntity orderDiscountType;

    private ProductDiscountTypeEntity productDiscountType;
    private Long discountAmount;
    List<DiscountedProducts> discountedProductsList;
    List<DiscountedCollections> discountedCollectionsList;

}
