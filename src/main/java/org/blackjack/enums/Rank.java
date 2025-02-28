package org.blackjack.enums;

import lombok.Getter;

@Getter
public enum Rank {
    TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"),
    FIVE(5, "5"), SIX(6, "6"), SEVEN(7, "7"),
    EIGHT(8, "8"), NINE(9, "9"), TEN(10, "10"),
    JACK(10, "J"), QUEEN(10, "Q"), KING(10, "K"),
    ACE(11, "A",1);

    private int primaryValue;
    private String displayValue;
    private Integer secondaryValue;

    Rank(int primaryValue, String displayValue) {
        this.primaryValue = primaryValue;
        this.displayValue = displayValue;
    }

    Rank(int primaryValue, String displayValue, Integer secondaryValue) {
        this.primaryValue = primaryValue;
        this.displayValue = displayValue;
        this.secondaryValue = secondaryValue;
    }
}
