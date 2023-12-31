package com.babyblackdog.ddogdog.coupon.controller.dto.response;

import com.babyblackdog.ddogdog.coupon.service.dto.result.ManualCouponFindResult;
import java.time.LocalDate;

public record ManualCouponFindResponse(Long couponUsageId, String couponName, String couponType, String discountType,
                                       Double discountValue, LocalDate endDate) {

    public static ManualCouponFindResponse of(ManualCouponFindResult manualCouponFindResult) {
        return new ManualCouponFindResponse(manualCouponFindResult.couponUsageId(),
                manualCouponFindResult.couponName().getValue(),
                manualCouponFindResult.couponType().name(), manualCouponFindResult.discountType().name(),
                manualCouponFindResult.discountValue(),
                manualCouponFindResult.endDate());
    }
}
