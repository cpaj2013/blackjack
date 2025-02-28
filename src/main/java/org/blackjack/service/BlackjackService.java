package org.blackjack.service;

import lombok.Getter;
import org.blackjack.constants.BlackjackConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Service
public class BlackjackService {

    DealerService dealerService;
    PlayerService playerService;
    DeckService deckService;

    public boolean gameInProgress = false;
    boolean dealersTurn = false;

    @Autowired
    public BlackjackService(DealerService dealerService, PlayerService playerService, DeckService deckService) {
        this.dealerService = dealerService;
        this.playerService = playerService;
        this.deckService = deckService;
    }

    public void startGame() {
        deckService.initializeDeck();
        dealerService.initializeHand();
        playerService.initializeHand();
        deckService.populateAndShuffleDeck();
        dealHands();
        displayAllPlayerAndDealerCards();
        gameInProgress = true;
    }

    // TODO create test
    public void dealHands() {
        // player dealer player dealer
        for (int i = 0; i < BlackjackConstants.MAX_STARTING_CARDS; i++) {
            playerService.addCardToHand(deckService.drawCard());
            dealerService.addCardToHand(deckService.drawCard());
        }
    }

    public void endGame() {
        this.gameInProgress = false;
        this.dealersTurn = true;
    }

    public boolean playerHit() {
        playerService.playerHit(deckService.drawCard());
        return playerService.didPlayerBust();
    }

    // TODO create test
    public String displayCardsAndScore(String player) {
        String stringValue;
        if (player.equals(BlackjackConstants.DEALER)) {
            stringValue = dealerService.handToString() + "|| " + dealerService.calculateSumOfHand();
        } else {
            stringValue = playerService.handToString() + "|| " + playerService.calculateSumOfHand();
        }

        System.out.println(player + " | " + stringValue);
        return stringValue;
    }

    public void displayAllPlayerAndDealerCards() {
        displayCardsAndScore(BlackjackConstants.DEALER);
        displayCardsAndScore(BlackjackConstants.PLAYER);
    }

    // dealer logic
    public void simulateDealerHand() {
        dealerService.simulateDealerHand(deckService.getDeck());
    }

    // check winner
    public String checkForWinner() {
        int dealerScore = dealerService.calculateSumOfHand();
        int playerScore = playerService.calculateSumOfHand();
        if (dealerScore > playerScore) {
            System.out.println("The dealer has won. " + dealerScore + " to " + playerScore);
            return BlackjackConstants.DEALER;
        } else if (dealerScore == playerScore) {
            System.out.println("Draw. " + dealerScore + " to " + playerScore);
            return BlackjackConstants.TIE;
        }
        System.out.println("The player has won. " + playerScore + " to " + dealerScore);
        return BlackjackConstants.PLAYER;
    }
}
