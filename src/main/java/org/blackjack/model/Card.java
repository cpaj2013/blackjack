package org.blackjack.model;

import lombok.Getter;
import lombok.Setter;
import org.blackjack.enums.Rank;
import org.blackjack.enums.Suit;

@Getter
@Setter
public class Card {
    private Rank rank;
    private Suit suit;
    private boolean flipped = false;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String toString() {
        return (flipped ? "X" : getRank().getDisplayValue() + getSuit().getSymbol());
    }
}
