package com.babyblackdog.ddogdog.coupon.domain.vo;

import static com.babyblackdog.ddogdog.global.exception.ErrorCode.INVALID_DISCOUNT_VALUE;

import com.babyblackdog.ddogdog.common.point.Point;
import com.babyblackdog.ddogdog.global.exception.CouponException;

public record Percent(Double value) implements Discountable {

    @Override
    public Point getDiscountAmount(Point originalPrice) {
        long originalValue = originalPrice.getValue();
        double discountAmount = originalValue * (value / 100);
        return new Point(Math.round(discountAmount));
    }

    public Percent {
        assertValidPercentage(value);
    }

    private void assertValidPercentage(Double value) {
        if (value < 1 || value > 100) {
            throw new CouponException(INVALID_DISCOUNT_VALUE);
        }
    }
}
