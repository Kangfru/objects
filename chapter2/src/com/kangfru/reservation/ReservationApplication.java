package com.kangfru.reservation;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public class ReservationApplication {

    public static void main(String[] args) {
        Movie avatar = new Movie("avatar",
                Duration.ofMinutes(120),
                Money.wons(10_000),
                new AmountDiscountPolicy(Money.wons(800),
                        new SequenceCondition(1),
                        new SequenceCondition(10),
                        new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 59)),
                        new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(20, 59))));

        Movie titanic = new Movie("타이타닉",
                Duration.ofMinutes(180),
                Money.wons(11_000),
                new PercentDiscountPolicy(0.1,
                        new SequenceCondition(1),
                        new SequenceCondition(10),
                        new PeriodCondition(DayOfWeek.TUESDAY, LocalTime.of(14, 0), LocalTime.of(16, 59)),
                        new SequenceCondition(2),
                        new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(13, 59))));

        // Movie 클래스 내부에선 DiscountPolicy에만 의존하도록 되어 있으나. 실행 시점에는 각 AmountDiscountPolicy, PercentDiscountPolicy의 인스턴스에 의존한다.
        // 이를 런타임 의존성이라고 하며, 코드의 의존성과 실행 시점의 의존성이 서로 다를 수 있다.
        // 코드의 의존성과 실행 시점의 의존성이 다르면 다를수록 코드는 이해하기 어려워진다. 코드를 이해하기 위해서는 코드뿐만 아니라 객체를 생성하고 연결하는 부분을 찾아야 하기 때문.
        // 반면 코드의 의존성과 실행 시점의 의존성이 다르면 다를 수록 코드는 더 유연해지고 확장 가능해진다. 이같은 의존성의 양면성은 설계가 트레이드 오프의 산물이라는 사실을 잘 보여준다.

        Movie startWars = new Movie("스타워즈",
                Duration.ofMinutes(210),
                Money.wons(10_000),
                new NoneDiscountPolicy());
    }

}
