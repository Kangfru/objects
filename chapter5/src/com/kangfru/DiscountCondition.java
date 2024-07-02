package com.kangfru;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {

    private DiscountConditionType type;
    private int sequence;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public boolean isSatisfiedBy(Screening screening) {
        if (type == DiscountConditionType.PERIOD) {
            return isSatisfiedByPeriod(screening);
        }

        return isSatisfiedBySequence(screening);
    }

    private boolean isSatisfiedByPeriod(Screening screening) {
        return dayOfWeek.equals(screening.getWhenScreened().gatDayOfWeek())
                && startTime.isBefore(screeing.getWhenScreened().toLocalTime())
                && endTime.isAfter(screening.getWhenScreend().toLocalTime());
    }

    private boolean isSatisfiedBySequence(Screening screening) {
        return sequence == screening.getSequence();
    }

}
