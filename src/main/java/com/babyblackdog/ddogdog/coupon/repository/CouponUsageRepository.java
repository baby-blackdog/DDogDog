package com.babyblackdog.ddogdog.coupon.repository;

import com.babyblackdog.ddogdog.coupon.domain.CouponUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponUsageRepository extends JpaRepository<CouponUsage, Long> {

}