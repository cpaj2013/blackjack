package org.blackjack.service;

import org.blackjack.enums.Rank;
import org.blackjack.enums.Suit;
import org.blackjack.model.Card;
import org.blackjack.model.Hand;
import org.blackjack.utilities.HandUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class DealerServiceTest {

    DealerService dealerService;

    @Test
    public void testDealerHitGreaterThanLimitReturnsFalse() {
        dealerService = new DealerService();

        Card cardOne = new Card(Rank.SEVEN, Suit.CLUB);
        Card cardTwo = new Card(Rank.TEN, Suit.CLUB);

        dealerService.dealerHit(cardOne);
        dealerService.dealerHit(cardTwo);

        Card cardThree = new Card(Rank.KING, Suit.CLUB);

        Assertions.assertFalse(dealerService.dealerHit(cardThree));
    }

    @Test
    public void testDealerHitGreaterThanLimitReturnsTrue() {
        dealerService = new DealerService();

        Card cardOne = new Card(Rank.SEVEN, Suit.CLUB);
        Card cardTwo = new Card(Rank.TWO, Suit.CLUB);

        dealerService.dealerHit(cardOne);
        dealerService.dealerHit(cardTwo);

        Card cardThree = new Card(Rank.TWO, Suit.HEART);

        Assertions.assertTrue(dealerService.dealerHit(cardThree));
    }
}
