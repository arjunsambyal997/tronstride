package com.tronstride.loyalty.repository;

import com.tronstride.loyalty.model.TimeSpanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSpanRepo extends JpaRepository<TimeSpanEntity, Integer> {
    TimeSpanEntity getByType(String type);
}
