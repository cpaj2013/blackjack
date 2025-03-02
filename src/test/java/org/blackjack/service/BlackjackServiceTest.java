package org.blackjack.service;

import org.blackjack.constants.BlackjackConstants;
import org.blackjack.enums.Rank;
import org.blackjack.enums.Suit;
import org.blackjack.model.Card;
import org.blackjack.model.Hand;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class BlackjackServiceTest {

    BlackjackService blackjackService;

    @Mock
    DealerService dealerService;
    @Mock
    PlayerService playerService;
    @Mock
    DeckService deckService;

    @BeforeEach
    public void beforeEach() {
        blackjackService = new BlackjackService(dealerService, playerService, deckService);
    }

    @Test
    public void testCheckForWinnerDealerBustPlayerWins() {
        blackjackService = new BlackjackService(dealerService, playerService, deckService);

        Mockito.when(dealerService.calculateSumOfHand()).thenReturn(25);
        Mockito.when(playerService.calculateSumOfHand(any())).thenReturn(18);
        Mockito.when(dealerService.didDealerBust()).thenReturn(true);

        String result = blackjackService.checkForWinner(new Hand());

        Assertions.assertEquals(BlackjackConstants.WIN, result);
    }


    @Test
    public void testCheckForWinnerPlayerBusts() {
        blackjackService = new BlackjackService(dealerService, playerService, deckService);

        Mockito.when(playerService.calculateSumOfHand(any())).thenReturn(25);
        Mockito.when(dealerService.calculateSumOfHand()).thenReturn(18);
        Mockito.when(playerService.didPlayerHandBust(any())).thenReturn(true);

        String result = blackjackService.checkForWinner(new Hand());

        Assertions.assertEquals(BlackjackConstants.BUST, result);
    }

    @Test
    public void testCheckForWinnerDealerWins() {
        blackjackService = new BlackjackService(dealerService, playerService, deckService);

        Mockito.when(dealerService.calculateSumOfHand()).thenReturn(21);
        Mockito.when(playerService.calculateSumOfHand(any())).thenReturn(18);
        Mockito.when(dealerService.didDealerBust()).thenReturn(false);

        String result = blackjackService.checkForWinner(new Hand());

        Assertions.assertEquals(BlackjackConstants.LOSS, result);
    }

    @Test
    public void testCheckForWinnerTie() {
        blackjackService = new BlackjackService(dealerService, playerService, deckService);

        Mockito.when(dealerService.calculateSumOfHand()).thenReturn(18);
        Mockito.when(playerService.calculateSumOfHand(any())).thenReturn(18);

        String result = blackjackService.checkForWinner(new Hand());

        Assertions.assertEquals(BlackjackConstants.TIE, result);
    }

    @Test
    public void testCheckForWinnerPlayerWins() {
        blackjackService = new BlackjackService(dealerService, playerService, deckService);

        Mockito.when(dealerService.calculateSumOfHand()).thenReturn(18);
        Mockito.when(playerService.calculateSumOfHand(any())).thenReturn(21);

        String result = blackjackService.checkForWinner(new Hand());

        Assertions.assertEquals(BlackjackConstants.WIN, result);
    }

    @Test
    public void testGenerateCardAndScoreSummaryForDealerWhenPlayerTurnIsNotOverDoesNotShowScore() {
        String result = blackjackService.generateCardAndScoreSummary(BlackjackConstants.DEALER);
        Assertions.assertTrue(result.endsWith("XX"));
    }

    @Test
    public void testGenerateCardAndScoreSummaryForDealerWhenPlayerTurnIsOverShowsScore() {
        blackjackService.setPlayerTurnOver(true);

        Mockito.when(dealerService.calculateSumOfHand()).thenReturn(10);

        String result = blackjackService.generateCardAndScoreSummary(BlackjackConstants.DEALER);
        Assertions.assertTrue(result.endsWith("10"));
    }

    @Test
    public void testGenerateCardAndScoreSummaryForPlayerSingleHandShowsScore() {
        List<Hand> hands = new ArrayList<>();
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.TEN, Suit.CLUB));
        hands.add(hand);

        Mockito.when(playerService.calculateSumOfHand(any())).thenReturn(10);
        Mockito.when(playerService.getPlayerHandList()).thenReturn(hands);

        String result = blackjackService.generateCardAndScoreSummary(BlackjackConstants.PLAYER);
        Assertions.assertTrue(result.endsWith("Score: 10 || "));
    }

    @Test
    public void testPlayerSplitCannotSplitHandReturnsFalse() {
        Mockito.when(playerService.canSplitHand(any())).thenReturn(false);

        Assertions.assertFalse(blackjackService.playerSplit());
    }

    @Test
    public void testPlayerSplitCanSplitHandReturnsTrue() {
        Mockito.when(playerService.canSplitHand(any())).thenReturn(true);

        Assertions.assertTrue(blackjackService.playerSplit());
    }

    @Test
    public void testPlayerHitHandBustsIncrementsCurrentHandIndex() {
        Mockito.when(playerService.didPlayerHandBust(any())).thenReturn(true);

        Assertions.assertFalse(blackjackService.playerHit());
    }

    @Test
    public void testPlayerHitNoHandBustReturnsTrue() {
        Mockito.when(playerService.didPlayerHandBust(any())).thenReturn(false);

        Assertions.assertTrue(blackjackService.playerHit());
    }

    // TODO add more test coverage for generate card and score summary
}
