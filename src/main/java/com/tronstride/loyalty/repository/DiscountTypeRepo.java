package com.tronstride.loyalty.repository;

import com.tronstride.loyalty.model.DiscountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountTypeRepo extends JpaRepository<DiscountTypeEntity, Integer> {
}
