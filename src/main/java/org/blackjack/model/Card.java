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
    private boolean faceUp = true;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public boolean flipCard() {
        faceUp = !faceUp;
        return faceUp;
    }

    public String toString() {
        return (faceUp ? getRank().getDisplayValue() + getSuit().getSymbol() : "X");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Card card = (Card) obj;
        return rank.equals(card.rank);
    }

    @Override
    public int hashCode() {
        return rank.hashCode();
    }
}
