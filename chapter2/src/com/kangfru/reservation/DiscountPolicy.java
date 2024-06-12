package com.kangfru.reservation;

public interface DiscountPolicy {

    Money calculateDiscountAmount(Screening screening);

}
