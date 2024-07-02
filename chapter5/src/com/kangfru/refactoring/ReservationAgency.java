package com.kangfru.refactoring;

public class ReservationAgency {

    public Reservation reservation(Screening screening, Customer customer, int audienceCount) {
        Movie movie = screening.getMovie();
        calcuateFee(screening, movie);
        return new Reservation(customer, screening, fee, audienceCount);
    }

    private static void calcuateFee(Screening screening, Movie movie) {
        if (isDiscountable(screening, movie, discountable)) {
            Money discountAmount = Money.ZERO;
            switch (movie.getMovieType()) {
                case AMOUNT_DISCOUNT:
                    discountAmount = movie.getDiscountAmount();
                    break;
                case PERCENT_DISCOUNT:
                    discountAmount = movie.getFee().times(movie.getDiscountPercent());
                    break;
                case NONE_DISCOUNT:
                    discountAmount = Money.ZERO;
                    break;
            }
            fee = movie.getFee().minus(discountAmount);
        } else {
            fee = movie.getFee();
        }
    }

    private static boolean isDiscountable(Screening screening, Movie movie, boolean discountable) {
        for (DiscountCondition condition : movie.getDiscountConditions()) {
            if (condition.getType() == DiscountConditionType.PERIOD) {
                discountable = isSatiesfiedByPeriod(screening, condition);
            } else {
                discountable = isSatiesfiedBySequence(screening, condition);
            }

            if (discountable) {
                break;
            }
        }
        return discountable;
    }


    // 아래 두 메서드는 DiscountCondition 으로 옮긴다.
    // -> 자신이 소유하고 있는 데이터를 자기 스스로 처리하도록 만드는 것이 자율적인 객체를 만드는 지름길이다.
    private static boolean isSatiesfiedBySequence(Screening screening, DiscountCondition condition) {
        boolean discountable;
        discountable = condition.getSequence() == screening.getSequence();
        return discountable;
    }

    private static boolean isSatiesfiedByPeriod(Screening screening, DiscountCondition condition) {
        boolean discountable;
        discountable = screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek())
                && condition.getStartTime().compareTo(screening.getWhenScreened().toLocalTime()) <= 0
                && condition.getEndTime().compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
        return discountable;
    }

}
