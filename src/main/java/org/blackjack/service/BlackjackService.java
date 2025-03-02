package org.blackjack.service;

import org.blackjack.constants.BlackjackConstants;
import org.blackjack.model.Hand;
import org.blackjack.utilities.CLIOutputUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        // Initial game setup
        deckService.initializeDeck();
        dealerService.initializeDealer();
        playerService.initializePlayer();
        setPlayerTurnOver(false);

        // card setup
        deckService.populateAndShuffleDeck();
        initialDeal();
    }

    public String endGame() {
        // if the player didn't bust but stood on all decks, their turn is now over
        setPlayerTurnOver(true);
        dealerService.flipAllCardsFaceUp();
        return generatePlayerAndDealerCardAndScoreSummary();
    }

    // TODO create test
    public void initialDeal() {
        // player dealer player dealer
        for (int i = 0; i < BlackjackConstants.DEFAULT_MAX_STARTING_CARDS; i++) {
            // Current hand for player should start at 0.
            playerService.addCard(playerService.getCurrentHand(), deckService.drawCard());
            dealerService.addCardToHand(deckService.drawCard());
        }
    }

    /**
     * Handles player stand functionality.
     *
     * @return True if player does not have another hand.
     */
    // TODO test coverage
    public boolean playerStand() {
        playerService.playerStand();
        return playerService.hasNextHand();
    }

    /**
     * Handles calls to the PlayerService to handle split functionality.
     *
     * @return False if hand cannot be split.
     */
    //TODO tests needed
    public boolean playerSplit() {
        if (!playerService.canSplitHand(playerService.getCurrentHand()))
            return false;

        Hand currentHand = playerService.getCurrentHand();
        Hand newHand = playerService.splitHand(currentHand);

        playerService.addCardToCurrentHand(deckService.drawCard());
        playerService.addCard(newHand, deckService.drawCard());
        return true;
    }

    /**
     * Handles player hit functionality. If the player hand busts,
     * increment the players current hand index.
     *
     * @return False if player does not have another hand.
     */
    public boolean playerHit() {
        playerService.playerHit(playerService.getCurrentHand(), deckService.drawCard());

        if (!playerService.didPlayerHandBust(playerService.getCurrentHand())) {
            return true;
        }
        playerService.incrementCurrentHand();

        return playerService.hasNextHand();
    }

    public String generateCardAndScoreSummary(String player) {
        StringBuilder stringValue = new StringBuilder(player + ":\n");
        if (player.equals(BlackjackConstants.DEALER)) {
            stringValue.append(dealerService.handToString()).append("|| ")
                    .append("Score: ").append((isPlayerTurnOver() ? dealerService.calculateSumOfHand() : "XX"));
        } else {
            List<Hand> hands = playerService.getPlayerHandList();
            for (int i = 0; i < hands.size(); i++) {
                stringValue.append(BlackjackConstants.HAND).append(" ").append(i).append(" ")
                        .append(playerService.handToString(hands.get(i))).append(" || ")
                        .append("Score: ").append(playerService.calculateSumOfHand(hands.get(i))).append(" || ")
                        .append(isPlayerTurnOver() ? checkForWinner(hands.get(i)) : "")
                        .append(playerService.isCurrentHand(hands.get(i)) && !isPlayerTurnOver()
                                ? BlackjackConstants.CURRENT_HAND_IDENTIFIER : "");
            }
        }
        return stringValue.toString();
    }

    public String generatePlayerAndDealerCardAndScoreSummary() {
        return CLIOutputUtils.generateMultiLineOutputString(generateCardAndScoreSummary(
                BlackjackConstants.DEALER)
                , generateCardAndScoreSummary(BlackjackConstants.PLAYER));
    }

    // TODO set logic to check for a player bust, maybe this can eliminate some extra code
    public void simulateDealerHand() {
        setPlayerTurnOver(true);
        dealerService.simulateDealerHand(deckService.getDeck());
    }

    // TODO create test coverage
    public String checkForWinner(Hand playerHand) {
        int dealerScore = dealerService.calculateSumOfHand();
        int playerHandScore = playerService.calculateSumOfHand(playerHand);

        if (playerHandScore > dealerScore && !playerService.didPlayerHandBust(playerHand)
                || dealerService.didDealerBust()) {
            return BlackjackConstants.WIN;
        }
        else if (dealerScore > playerHandScore && !dealerService.didDealerBust()
                && !playerService.didPlayerHandBust(playerHand)) {
            return BlackjackConstants.LOSS;
        }
        else if (playerHandScore == dealerScore) {
            return BlackjackConstants.TIE;
        } else {
            return BlackjackConstants.BUST;
        }
    }

    public void setPlayerTurnOver(boolean playerTurnOver) {
        this.playerTurnOver = playerTurnOver;
    }

    public boolean isPlayerTurnOver() {
        return playerTurnOver;
    }
}
