package com.babyblackdog.ddogdog.coupon.domain.vo;

import static com.babyblackdog.ddogdog.global.exception.ErrorCode.INVALID_DISCOUNT_TYPE;

import com.babyblackdog.ddogdog.common.point.Point;
import com.babyblackdog.ddogdog.global.exception.CouponException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class DiscountValue {

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type")
    private DiscountType discountType;

    @Column(name = "discount_value")
    private Double value;

    private DiscountValue(DiscountType discountType, Discountable discount) {
        this.discountType = discountType;
        this.value = discount.value();
    }

    protected DiscountValue() {
    }

    public static DiscountValue from(String discountTypeStr, Double discountValue) {
        return switch (discountTypeStr.toLowerCase()) {
            case "amount" -> new DiscountValue(DiscountType.AMOUNT, new Amount(discountValue));
            case "percent" -> new DiscountValue(DiscountType.PERCENT, new Percent(discountValue));
            default -> throw new CouponException(INVALID_DISCOUNT_TYPE);
        };
    }

    public Point getDiscount(Point originalPrice) {
        return getDiscountable().getDiscountAmount(originalPrice);
    }

    public Double getValue() {
        return value;
    }

    public DiscountType getType() {
        return discountType;
    }

    private Discountable getDiscountable() {
        return switch (discountType) {
            case AMOUNT -> new Amount(value);
            case PERCENT -> new Percent(value);
        };
    }

}
