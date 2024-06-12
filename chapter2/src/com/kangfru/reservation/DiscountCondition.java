package com.kangfru.reservation;

public interface DiscountCondition {

    boolean isSatisfiedBy(Screening screening);

}
