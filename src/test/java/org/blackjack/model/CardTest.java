package org.blackjack.model;

import org.blackjack.enums.Rank;
import org.blackjack.enums.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    public void testCardToStringIsFaceUp() {
        Card card = new Card(Rank.SEVEN, Suit.CLUB);

        String result = card.toString();
        Assertions.assertFalse(result.contains("X"));
    }

    @Test
    public void testCardToStringIsNotFaceUp() {
        Card card = new Card(Rank.SEVEN, Suit.CLUB);
        card.setFaceUp(false);

        String result = card.toString();
        Assertions.assertEquals("X", result);
    }

    @Test
    public void testEqualsExactSameIsTrue() {
        Card c1 = new Card(Rank.SEVEN, Suit.HEART);

        Assertions.assertEquals(c1, c1);
    }

    @Test
    public void testEqualsSameRankDiffSuitIsTrue() {
        Card c1 = new Card(Rank.SEVEN, Suit.HEART);
        Card c2 = new Card(Rank.SEVEN, Suit.SPADE);

        Assertions.assertEquals(c1, c2);
    }

    @Test
    public void testEqualsDifferentRankIsFalse() {
        Card c1 = new Card(Rank.ACE, Suit.HEART);
        Card c2 = new Card(Rank.SEVEN, Suit.SPADE);

        Assertions.assertNotEquals(c1, c2);
    }
}
