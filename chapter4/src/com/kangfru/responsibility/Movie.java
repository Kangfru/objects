package com.kangfru.responsibility;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Movie {

    private String title;
    private Duration runningTime;
    private Money fee;

    // 앞선 2장에서 살펴봤던 것과 달리 할인 조건의 목록이 인스턴스 변수로 포함된다
    // 또한 할인 금액과 할인 비율 역시 Movie 에서 직접 정의 중이다.
    private List<DiscountCondition> discountConditions;
//  movieType처럼 객체의 종류를 저장하는 변수와 인스턴스의 종류에 따라 배타적으로 사용될 인스턴스 변수 (discountAmount, discountPercent)를
    // 하나의 클래스 안에 포함하는 방식이 일반적으로 데이터 중심 설계에서 보기 쉬운 방식이다.
    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    // 1. 어떤 데이터를 포함해야 하는가?
    // 2. 어떤 오퍼레이션이 필요한가?

    public com.kangfru.data.MovieType getMovieType() {
        return movieType;
    }

    public Money calculateAmountDiscountedFee() {
        if (movieType != MovieType.AMOUNT_DISCOUNT) {
            throw new IllegalArgumentException();
        }
        return fee.minus(discountAmount);
    }

    public Money calculatePercentDiscountedFee() {
        if (movieType != MovieType.PERCENT_DISCOUNT) {
            throw new IllegalArgumentException();
        }
        return fee.minus(fee.times(discountPercent));
    }

    public Money calculateNoneDiscountedFee() {
        if (movieType != MovieType.NONE_DISCOUNT) {
            throw new IllegalArgumentException();
        }
        return fee;
    }

    public boolean isDiscountable(LocalDateTime whenScreened, int sequence) {
        for (DiscountCondition condition : discountConditions) {
            if (condition.getType() == DiscountConditionType.PERIOD) {
                if (condition.isDiscountable(whenScreened.getDayOfWeek(), whenScreened.toLocalTime())) {
                    return true;
                }
            } else {
                if (condition.isDiscountable(sequence)) {
                    return true;
                }
            }
        }
        return false;
    }
}
