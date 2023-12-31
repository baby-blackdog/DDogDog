package com.babyblackdog.ddogdog.coupon.domain.vo;

import static com.babyblackdog.ddogdog.global.exception.ErrorCode.INVALID_DISCOUNT_VALUE;

import com.babyblackdog.ddogdog.common.point.Point;
import com.babyblackdog.ddogdog.global.exception.CouponException;

public record Amount(Double value) implements Discountable {

    public Amount {
        assertHasNoDecimalPart(value);
        assertHasPositiveValue(value);
    }

    @Override
    public Point getDiscountAmount(Point originalPrice) {
        if (discountAmountIsBiggerThanOriginalPrice(originalPrice)) {
            return originalPrice;
        }

        return new Point(value().longValue());
    }

    private boolean discountAmountIsBiggerThanOriginalPrice(Point originalPrice) {
        return originalPrice.getValue() < value().longValue();
    }

    private void assertHasNoDecimalPart(Double value) {
        if (value != Math.floor(value)) {
            throw new CouponException(INVALID_DISCOUNT_VALUE);
        }
    }

    private void assertHasPositiveValue(Double value) {
        if (value <= 0) {
            throw new CouponException(INVALID_DISCOUNT_VALUE);
        }
    }
}
