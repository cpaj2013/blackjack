package org.blackjack.service;

import org.blackjack.enums.Rank;
import org.blackjack.enums.Suit;
import org.blackjack.model.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DealerServiceTest {

    DealerService dealerService;

    @Test
    public void testAddCardToHandFlipsFirstCardAndNoOther() {
        dealerService = new DealerService();

        Card cardOne = new Card(Rank.SEVEN, Suit.CLUB);
        Card cardTwo = new Card(Rank.TWO, Suit.CLUB);

        dealerService.addCardToHand(cardOne);
        dealerService.addCardToHand(cardTwo);

        Assertions.assertFalse(cardOne.isFaceUp());
        Assertions.assertTrue(cardTwo.isFaceUp());
    }

    @Test
    public void testSimulateDealerHandAllCardsFaceUp() {
        dealerService = new DealerService();

        DeckService deckService = new DeckService();
        deckService.populateAndShuffleDeck();

        dealerService.addCardToHand(deckService.drawCard());
        dealerService.addCardToHand(deckService.drawCard());

        dealerService.simulateDealerHand(deckService.getDeck());

        for (Card c : dealerService.getHand().getCardList()) {
            Assertions.assertTrue(c.isFaceUp());
        }
    }

    @Test
    public void testSimulateDealerHandCardAddedLowerThanMaxScoreLimit17() {
        dealerService = new DealerService();

        DeckService deckService = new DeckService();
        deckService.populateAndShuffleDeck();

        dealerService.addCardToHand(new Card(Rank.TEN, Suit.CLUB));
        dealerService.addCardToHand(new Card(Rank.SIX, Suit.HEART));

        dealerService.simulateDealerHand(deckService.getDeck());
        Assertions.assertEquals(3, dealerService.getHand().size());
    }

    @Test
    public void testSimulateDealerHandCardNotAddedHigherThanMaxScoreLimit17() {
        dealerService = new DealerService();

        DeckService deckService = new DeckService();
        deckService.populateAndShuffleDeck();

        dealerService.addCardToHand(new Card(Rank.TEN, Suit.CLUB));
        dealerService.addCardToHand(new Card(Rank.TEN, Suit.HEART));

        dealerService.simulateDealerHand(deckService.getDeck());
        Assertions.assertEquals(2, dealerService.getHand().size());
    }

}
