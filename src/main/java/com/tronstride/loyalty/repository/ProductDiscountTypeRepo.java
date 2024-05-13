package com.tronstride.loyalty.repository;

import com.tronstride.loyalty.model.ProductDiscountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDiscountTypeRepo extends JpaRepository<ProductDiscountTypeEntity, Integer> {
    ProductDiscountTypeEntity getByProductDiscountType(String productDiscountType);
}
