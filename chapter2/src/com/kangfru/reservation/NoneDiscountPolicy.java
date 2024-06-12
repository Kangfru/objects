package com.kangfru.reservation;

/**
 * 기존의 Movie와 DiscountPolicy는 전혀 수정하지 않은 채 NoneDiscountPolicy 만을 추가해 어플리케이션에 새로운 기능을 추가해 확장했다.
 */
public class NoneDiscountPolicy implements DiscountPolicy {


    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
