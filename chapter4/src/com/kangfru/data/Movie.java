package com.kangfru.data;

import com.kangfru.responsibility.DiscountCondition;
import com.kangfru.responsibility.MovieType;

import java.time.Duration;

public class Movie {

    private String title;
    private Duration runningTime;
    private Money fee;

    // 앞선 2장에서 살펴봤던 것과 달리 할인 조건의 목록이 인스턴스 변수로 포함된다
    // 또한 할인 금액과 할인 비율 역시 Movie 에서 직접 정의 중이다.
    private List<com.kangfru.responsibility.DiscountCondition> discountConditions;
//  movieType처럼 객체의 종류를 저장하는 변수와 인스턴스의 종류에 따라 배타적으로 사용될 인스턴스 변수 (discountAmount, discountPercent)를
    // 하나의 클래스 안에 포함하는 방식이 일반적으로 데이터 중심 설계에서 보기 쉬운 방식이다.
    private com.kangfru.responsibility.MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Duration runningTime) {
        this.runningTime = runningTime;
    }

    public Money getFee() {
        return fee;
    }

    public void setFee(Money fee) {
        this.fee = fee;
    }

    public List<com.kangfru.responsibility.DiscountCondition> getDiscountConditions() {
        return discountConditions;
    }

    public void setDiscountConditions(List<DiscountCondition> discountConditions) {
        this.discountConditions = discountConditions;
    }

    public com.kangfru.responsibility.MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public Money getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }
}
