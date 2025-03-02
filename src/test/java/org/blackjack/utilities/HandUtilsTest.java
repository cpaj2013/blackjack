package org.blackjack.utilities;

import org.blackjack.enums.Rank;
import org.blackjack.enums.Suit;
import org.blackjack.model.Card;
import org.blackjack.model.Hand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HandUtilsTest {
    @Test
    public void testCalculateSumOfAcesShouldUseHighValue() {
        Hand hand = new Hand();

        Card cardOne = new Card(Rank.TWO, Suit.CLUB);
        Card cardTwo = new Card(Rank.ACE, Suit.CLUB);

        hand.addCard(cardOne);
        hand.addCard(cardTwo);

        int result = HandUtils.calculateSumOfHand(hand);
        Assertions.assertEquals(13, result);
    }

    @Test
    public void testCalculateSumOfAcesShouldUseLowValue() {
        Hand hand = new Hand();

        Card cardOne = new Card(Rank.TEN, Suit.CLUB);
        Card cardTwo = new Card(Rank.ACE, Suit.CLUB);
        Card cardThree = new Card(Rank.FIVE, Suit.CLUB);


        hand.addCard(cardOne);
        hand.addCard(cardTwo);
        hand.addCard(cardThree);

        int result = HandUtils.calculateSumOfHand(hand);
        Assertions.assertEquals(16, result);
    }

    @Test
    public void testCalculateSumOfHand() {
        Hand hand = new Hand();

        Card cardOne = new Card(Rank.TWO, Suit.CLUB);
        Card cardTwo = new Card(Rank.FIVE, Suit.CLUB);

        hand.addCard(cardOne);
        hand.addCard(cardTwo);

        int result = HandUtils.calculateSumOfHand(hand);
        Assertions.assertEquals(7, result);
    }

    @Test
    public void testCheckForBustTrue() {
        Hand hand = new Hand();
        hand.getCardList().add(new Card(Rank.KING, Suit.CLUB));
        hand.getCardList().add(new Card(Rank.KING, Suit.HEART));
        hand.getCardList().add(new Card(Rank.KING, Suit.SPADE));

        Assertions.assertTrue(HandUtils.checkForBust(hand));
    }

    @Test
    public void testCheckForBustFalse() {
        Hand hand = new Hand();
        hand.getCardList().add(new Card(Rank.KING, Suit.CLUB));
        hand.getCardList().add(new Card(Rank.KING, Suit.HEART));

        Assertions.assertFalse(HandUtils.checkForBust(hand));
    }
}
