package com.tronstride.loyalty.repository;

import com.tronstride.loyalty.model.DiscountedCollections;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountedCollectionsRepo extends JpaRepository<DiscountedCollections,Integer> {
}
