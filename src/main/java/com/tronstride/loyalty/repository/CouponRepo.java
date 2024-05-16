package com.tronstride.loyalty.repository;

import com.tronstride.loyalty.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

}
