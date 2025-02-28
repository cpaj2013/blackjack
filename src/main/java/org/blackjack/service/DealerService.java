package org.blackjack.service;

import org.blackjack.constants.BlackjackConstants;
import org.blackjack.model.Card;
import org.blackjack.model.Deck;
import org.blackjack.model.Hand;
import org.blackjack.utilities.HandUtils;
import org.springframework.stereotype.Service;

@Service
public class DealerService {

    private Hand hand;

    public DealerService() {
        this.hand = new Hand();
    }

    // TODO fix issue where dealer draws card even though it may not use it
   public boolean dealerHit(Card card) {
        if (HandUtils.calculateSumOfHand(hand) < BlackjackConstants.DEALER_MAX_SCORE_HIT_LIMIT) {
            hand.addCard(card);
            return true;
        } else {
            System.out.println("Dealer must stand.");
            return false;
        }
    }

    public void dealerStand() {
        // TODO this should trigger end of game and calculate final results
    }

    public void addCardToHand(Card card) {
        hand.addCard(card);
    }

    // TODO create test
    public void simulateDealerHand(Deck deck) {
        while (HandUtils.calculateSumOfHand(hand) < BlackjackConstants.DEALER_MAX_SCORE_HIT_LIMIT) {
            dealerHit(deck.drawCard());
        }
    }

    public boolean didDealerBust() {
        return HandUtils.checkForBust(hand);
    }

    public String handToString() {
        return hand.toString();
    }

    public void initializeHand() {
        this.hand = new Hand();
    }

    public int calculateSumOfHand() {
        return HandUtils.calculateSumOfHand(hand);
    }

}
