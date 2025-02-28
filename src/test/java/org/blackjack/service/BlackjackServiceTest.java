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

        Assertions.assertEquals(BlackjackConstants.PLAYER, result);
    }

    @Test
    public void testCheckForWinnerDealerWins() {
        blackjackService = new BlackjackService(dealerService, playerService, deckService);

        Mockito.when(dealerService.calculateSumOfHand()).thenReturn(21);
        Mockito.when(playerService.calculateSumOfHand()).thenReturn(18);
        Mockito.when(dealerService.didDealerBust()).thenReturn(false);

        String result = blackjackService.checkForWinner();

        Assertions.assertEquals(BlackjackConstants.DEALER, result);
    }

    @Test
    public void testCheckForWinnerTie() {
        blackjackService = new BlackjackService(dealerService, playerService, deckService);

        Mockito.when(dealerService.calculateSumOfHand()).thenReturn(18);
        Mockito.when(playerService.calculateSumOfHand()).thenReturn(18);

        String result = blackjackService.checkForWinner();

        Assertions.assertEquals(BlackjackConstants.TIE, result);
    }

    @Test
    public void testCheckForWinnerPlayerWins() {
        blackjackService = new BlackjackService(dealerService, playerService, deckService);

        Mockito.when(dealerService.calculateSumOfHand()).thenReturn(18);
        Mockito.when(playerService.calculateSumOfHand()).thenReturn(21);

        String result = blackjackService.checkForWinner();

        Assertions.assertEquals(BlackjackConstants.PLAYER, result);
    }
}
