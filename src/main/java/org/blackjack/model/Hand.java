package org.blackjack.model;

import lombok.Getter;
import lombok.Setter;
import org.blackjack.constants.BlackjackConstants;

import java.util.ArrayList;

@Getter
@Setter
public class Hand {

    ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card removeCard(Card card) {
        hand.remove(card);
        return card;
    }

    // TODO create test
    public String toString() {
        String handAsString = " | ";
        for (Card c : hand) {
            handAsString = handAsString + c.getRank().getDisplayValue() + c.getSuit().getSymbol() + " | ";
        }
        return handAsString;
    }
}
