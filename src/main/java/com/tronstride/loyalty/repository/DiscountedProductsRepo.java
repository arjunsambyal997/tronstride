package com.tronstride.loyalty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountedProductsRepo extends JpaRepository<com.tronstride.loyalty.model.DiscountedProducts, Integer> {
}
