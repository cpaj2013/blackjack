package org.blackjack.service;

import org.blackjack.enums.Rank;
import org.blackjack.enums.Suit;
import org.blackjack.model.Card;
import org.blackjack.model.Hand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayerServiceTest {

    PlayerService playerService;

    @BeforeEach
    public void beforeEach() {
        playerService = new PlayerService();
    }

    @Test
    public void testSplitHandNotAllowedReturnsNull() {
        Hand hand = playerService.getCurrentHand();
        hand.addCard(new Card(Rank.SEVEN, Suit.CLUB));
        hand.addCard(new Card(Rank.EIGHT, Suit.HEART));

        Assertions.assertNull(playerService.splitHand(hand));
    }

    @Test
    public void testSplitHandNotAllowedReturnsNewHand() {
        Hand hand = playerService.getCurrentHand();
        hand.addCard(new Card(Rank.SEVEN, Suit.CLUB));
        Card splitCard = new Card(Rank.SEVEN, Suit.HEART);
        hand.addCard(splitCard);

        Assertions.assertEquals(1, playerService.splitHand(hand).size());
        Assertions.assertEquals(splitCard, playerService.getPlayerHandList().get(1).getCard(0));
    }

    @Test
    public void testCanSplitHandWhenMaxSplitLimitOf4ReachedReturnsFalse() {
        List<Hand> handList = playerService.getPlayerHandList();
        handList.add(new Hand());
        handList.add(new Hand());
        handList.add(new Hand());

        Assertions.assertEquals(4, handList.size());
        Assertions.assertFalse(playerService.canSplitHand(new Hand()));
    }

    @Test
    public void testCanSplitHandWhenMaxSplitLimitOf4ReachedReturnsTrue() {
        Hand hand = playerService.getCurrentHand();

        hand.addCard(new Card(Rank.SEVEN, Suit.HEART));
        hand.addCard(new Card(Rank.SEVEN, Suit.CLUB));

        Assertions.assertTrue(playerService.canSplitHand(hand));
    }

    @Test
    public void testHasNextHandReturnsFalse() {
        Assertions.assertFalse(playerService.hasNextHand());
    }

    @Test
    public void testHasNextHandReturnsTrue() {
        playerService.addHand(new Hand());
        Assertions.assertTrue(playerService.hasNextHand());
    }
}
