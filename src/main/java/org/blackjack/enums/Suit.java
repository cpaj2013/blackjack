package org.blackjack.enums;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public enum Suit {
    HEART("\u2665"),
    DIAMOND("\u2666"),
    SPADE("\u2660"),
    CLUB("\u2663");

    private String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }
}
