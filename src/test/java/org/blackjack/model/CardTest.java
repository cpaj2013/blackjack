package org.blackjack.model;

import org.blackjack.enums.Rank;
import org.blackjack.enums.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    public void testCardToStringNotFlipped() {
        Card card = new Card(Rank.SEVEN, Suit.CLUB);

        String result = card.toString();
        Assertions.assertNotEquals("X", result);
    }

    @Test
    public void testCardToStringIsFlipped() {
        Card card = new Card(Rank.SEVEN, Suit.CLUB);
        card.setFaceUp(true);

        String result = card.toString();
        Assertions.assertEquals("X", result);
    }
}
