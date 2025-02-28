package org.blackjack.model;

import org.blackjack.enums.Rank;
import org.blackjack.enums.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class HandTest {

    @Test
    public void testHandToString() {
        Hand hand = new Hand();

        Card c1 = new Card(Rank.SEVEN, Suit.CLUB);
        Card c2 = new Card(Rank.SIX, Suit.HEART);
        hand.addCard(c1);
        hand.addCard(c2);

        String result = hand.toString();
        Assertions.assertEquals(" | 7♣ | 6♥ | ", result);
    }
}
