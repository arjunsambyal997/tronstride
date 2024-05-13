package com.tronstride.loyalty.repository;

import com.tronstride.loyalty.model.OrderDiscountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDiscountTypeRepo extends JpaRepository<OrderDiscountTypeEntity, Integer> {
    OrderDiscountTypeEntity getByOrderDiscountType(String orderDiscountType);
}
