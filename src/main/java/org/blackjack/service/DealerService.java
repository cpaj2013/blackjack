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
    private boolean dealerHit(Card card) {
        addCardToHand(card);
        return didDealerBust();
    }

    public void addCardToHand(Card card) {
        if (hand.size() == 0) {
            card.flipCard();
        }
        hand.addCard(card);
    }

    public void simulateDealerHand(Deck deck) {
        if (!hand.isEmpty()) {
            hand.getCard(0).flipCard();
        }
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

    public void flipAllCardsFaceUp() {
        hand.flipAllCardsFaceUp();
    }

    public Hand getHand() {
        return hand;
    }

}
