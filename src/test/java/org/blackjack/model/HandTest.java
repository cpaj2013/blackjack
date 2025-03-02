package org.blackjack.model;

import org.blackjack.enums.Rank;
import org.blackjack.enums.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    @Test
    public void testFlipAllCardsFaceUp() {
        Hand hand = new Hand();

        Card c1 = new Card(Rank.SEVEN, Suit.CLUB);
        c1.flipCard();

        Card c2 = new Card(Rank.SEVEN, Suit.HEART);

        hand.addCard(c1);
        hand.addCard(c2);

        hand.flipAllCardsFaceUp();

        for (Card c : hand.getCardList()) {
            Assertions.assertTrue(c.isFaceUp());
        }
    }

    @Test
    public void testIsSplittableTrue() {
        Hand hand = new Hand();

        Card c1 = new Card(Rank.SEVEN, Suit.CLUB);
        Card c2 = new Card(Rank.SEVEN, Suit.HEART);

        hand.addCard(c1);
        hand.addCard(c2);

        Assertions.assertTrue(hand.isSplittable());
    }

    @Test
    public void testIsSplittableFalse() {
        Hand hand = new Hand();

        Card c1 = new Card(Rank.TEN, Suit.CLUB);
        Card c2 = new Card(Rank.SEVEN, Suit.HEART);

        hand.addCard(c1);
        hand.addCard(c2);

        Assertions.assertFalse(hand.isSplittable());
    }
}
