package org.blackjack.service;

import org.blackjack.constants.BlackjackConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BlackjackServiceTest {

    BlackjackService blackjackService;

    @Mock
    DealerService dealerService;
    @Mock
    PlayerService playerService;
    @Mock
    DeckService deckService;

    @Test
    public void testCheckForWinnerDealerBustPlayerWins() {
        blackjackService = new BlackjackService(dealerService, playerService, deckService);

        Mockito.when(dealerService.calculateSumOfHand()).thenReturn(25);
        Mockito.when(playerService.calculateSumOfHand()).thenReturn(18);
        Mockito.when(dealerService.didDealerBust()).thenReturn(true);

        String result = blackjackService.checkForWinner();

        Assertions.assertTrue(result.contains("The player has won."));
    }

    @Test
    public void testCheckForWinnerDealerWins() {
        blackjackService = new BlackjackService(dealerService, playerService, deckService);

        Mockito.when(dealerService.calculateSumOfHand()).thenReturn(21);
        Mockito.when(playerService.calculateSumOfHand()).thenReturn(18);
        Mockito.when(dealerService.didDealerBust()).thenReturn(false);

        String result = blackjackService.checkForWinner();

        Assertions.assertTrue(result.contains("The dealer has won."));
    }

    @Test
    public void testCheckForWinnerTie() {
        blackjackService = new BlackjackService(dealerService, playerService, deckService);

        Mockito.when(dealerService.calculateSumOfHand()).thenReturn(18);
        Mockito.when(playerService.calculateSumOfHand()).thenReturn(18);

        String result = blackjackService.checkForWinner();

        Assertions.assertTrue(result.contains("Draw."));
    }

    @Test
    public void testCheckForWinnerPlayerWins() {
        blackjackService = new BlackjackService(dealerService, playerService, deckService);

        Mockito.when(dealerService.calculateSumOfHand()).thenReturn(18);
        Mockito.when(playerService.calculateSumOfHand()).thenReturn(21);

        String result = blackjackService.checkForWinner();

        Assertions.assertTrue(result.contains("The player has won."));
    }

    @Test
    public void testGenerateCardAndScoreSummaryForDealerWhenPlayerTurnIsNotOverDoesNotShowScore() {
        blackjackService = new BlackjackService(dealerService, playerService, deckService);

        String result = blackjackService.generateCardAndScoreSummary(BlackjackConstants.DEALER);
        Assertions.assertTrue(result.endsWith("XX"));
    }

    @Test
    public void testGenerateCardAndScoreSummaryForDealerWhenPlayerTurnIsOverShowsScore() {
        blackjackService = new BlackjackService(dealerService, playerService, deckService);
        blackjackService.setPlayerTurnOver(true);

        Mockito.when(dealerService.calculateSumOfHand()).thenReturn(10);

        String result = blackjackService.generateCardAndScoreSummary(BlackjackConstants.DEALER);
        Assertions.assertTrue(result.endsWith("10"));
    }

    @Test
    public void testGenerateCardAndScoreSummaryForPlayerShowsScore() {
        blackjackService = new BlackjackService(dealerService, playerService, deckService);

        Mockito.when(playerService.calculateSumOfHand()).thenReturn(10);

        String result = blackjackService.generateCardAndScoreSummary(BlackjackConstants.PLAYER);
        Assertions.assertTrue(result.endsWith("10"));
    }
}
