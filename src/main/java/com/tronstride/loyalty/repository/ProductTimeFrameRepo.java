package com.tronstride.loyalty.repository;

import com.tronstride.loyalty.model.Timeframe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTimeFrameRepo extends JpaRepository<Timeframe,Integer> {
}
