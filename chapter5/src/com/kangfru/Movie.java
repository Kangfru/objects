package com.kangfru;

import java.time.Duration;

public class Movie {

    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    public Money calculateMovieFee(Screening screening) {
        if (isDiscountable(screening)) {
            return fee.minus(calculateDiscountAmount());
        }
        return fee;
    }

    private boolean isDiscountable(Screening screening) {
        return discountConditions.stream()
                .anyMatch(condition -> condition.isSatisfiedBy(screening));
    }

    private Money calculateDiscountAmount() {
        switch (movieType) {
            case AMOUNT_DISCOUNT -> {
                return calculateAmountDiscountAmount();
            }
            case PERCENT_DISCOUNT -> {
                return calculatePercentDiscountAmount();
            }
            case NONE_DISCOUNT -> {
                return calculateNoneDiscountAmount();
            }
        }
        throw new IllegalStateException();
    }

    public Money calculateAmountDiscountAmount() {
        return discountAmount;
    }

    public Money calculatePercentDiscountAmount() {
        return fee.times(discountPercent);
    }
    public Money calculateNoneDiscountAmount() {
        return Money.ZERO;
    }

}
