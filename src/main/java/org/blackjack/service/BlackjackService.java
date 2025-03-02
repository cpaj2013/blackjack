package org.blackjack.service;

import lombok.Setter;
import org.blackjack.constants.BlackjackConstants;
import org.blackjack.utilities.CLIOutputUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlackjackService {

    private DealerService dealerService;
    private PlayerService playerService;
    private DeckService deckService;

    private boolean playerTurnOver = false;

    @Autowired
    public BlackjackService(DealerService dealerService, PlayerService playerService, DeckService deckService) {
        this.dealerService = dealerService;
        this.playerService = playerService;
        this.deckService = deckService;
    }

    // TODO create tests
    public void startGame() {
        deckService.initializeDeck();
        dealerService.initializeHand();
        playerService.initializeHand();
        setPlayerTurnOver(false);
        deckService.populateAndShuffleDeck();
        dealHands();
    }

    // TODO create test
    public void dealHands() {
        // player dealer player dealer
        for (int i = 0; i < BlackjackConstants.MAX_STARTING_CARDS; i++) {
            playerService.addCardToHand(deckService.drawCard());
            dealerService.addCardToHand(deckService.drawCard());
        }
    }

    // TODO create tests
    public boolean playerHit() {
        playerService.playerHit(deckService.drawCard());
        return playerService.didPlayerBust();
    }

    public String generateCardAndScoreSummary(String player) {
        String stringValue = player + " ";
        if (player.equals(BlackjackConstants.DEALER)) {
            return stringValue + dealerService.handToString() + "|| " +
                    (isPlayerTurnOver() ? dealerService.calculateSumOfHand() : "XX");
        } else {
            return stringValue + playerService.handToString() + "|| " + playerService.calculateSumOfHand();
        }
    }

    public String generatePlayerAndDealerCardAndScoreSummary() {
        return CLIOutputUtils.generateMultiLineOutputString(generateCardAndScoreSummary(BlackjackConstants.DEALER)
                , generateCardAndScoreSummary(BlackjackConstants.PLAYER));
    }

    // dealer logic
    public void simulateDealerHand() {
        setPlayerTurnOver(true);
        dealerService.simulateDealerHand(deckService.getDeck());
    }

    // check winner
    public String checkForWinner() {
        int dealerScore = dealerService.calculateSumOfHand();
        int playerScore = playerService.calculateSumOfHand();
        if (dealerScore > playerScore && !dealerService.didDealerBust()) {
            return CLIOutputUtils.generateOutputString
                            ("The dealer has won. " + dealerScore + " to " + playerScore
                    , BlackjackConstants.CLI_LINE_SEPARATOR);
        } else if (dealerScore == playerScore) {
            return CLIOutputUtils.generateOutputString("Draw. " + dealerScore + " to " + playerScore
                    , BlackjackConstants.CLI_LINE_SEPARATOR);
        }
        return CLIOutputUtils.generateOutputString("The player has won. " + playerScore + " to " + dealerScore
                , BlackjackConstants.CLI_LINE_SEPARATOR);
    }

    public String endGame() {
        setPlayerTurnOver(true);
        dealerService.flipAllCardsFaceUp();
        return generatePlayerAndDealerCardAndScoreSummary();
    }

    public void setPlayerTurnOver(boolean playerTurnOver) {
        this.playerTurnOver = playerTurnOver;
    }

    public boolean isPlayerTurnOver() {
        return playerTurnOver;
    }
}
