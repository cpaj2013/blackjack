package org.blackjack.service;

import org.blackjack.model.Card;
import org.blackjack.model.Hand;
import org.blackjack.utilities.HandUtils;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private Hand hand;

    public PlayerService() {
        this.hand = new Hand();
    }

    public void playerHit(Card card) {
        addCardToHand(card);
    }

    public boolean playerStand() {
        return true;
    }

    // TODO create test
    public void addCardToHand(Card card) {
        hand.addCard(card);
    }

    public boolean didPlayerBust() {
        return HandUtils.checkForBust(hand);
    }

    public String handToString() {
        return hand.toString();
    }

    // player logic

    // hit

    // stand

    // calculate hand value?

    // start new round
    public void initializeHand() {
        this.hand = new Hand();
    }

    public int calculateSumOfHand() {
        return HandUtils.calculateSumOfHand(hand);
    }

}
