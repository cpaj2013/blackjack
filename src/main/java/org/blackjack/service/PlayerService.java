package org.blackjack.service;

import org.blackjack.constants.BlackjackConstants;
import org.blackjack.model.Card;
import org.blackjack.model.Hand;
import org.blackjack.utilities.HandUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    // total hands = split count
    private List<Hand> playerHandList;
    private int currentHandIndex = 0;

    public PlayerService() {
        initializePlayer();
    }

    public void initializePlayer() {
        this.playerHandList = new ArrayList<>();
        this.playerHandList.add(new Hand());
        setCurrentHand(0);
    }

    public void playerHit(Hand hand, Card card) {
        addCard(hand, card);
    }

    /**
     * Handles stand activities. Increments current hand index if more exist.
     *
     * @return True if more hands exist.
     */
    public void playerStand() {
        incrementCurrentHand();
    }

    public void addCardToCurrentHand(Card card) {
        getCurrentHand().addCard(card);
    }

    // TODO create test
    public void addCard(Hand hand, Card card) {
        hand.addCard(card);
    }

    public Hand addHand(Hand hand) {
        playerHandList.add(hand);
        return hand;
    }

    public boolean didPlayerHandBust(Hand hand) {
        return HandUtils.checkForBust(hand);
    }

    public String handToString(Hand hand) {
        return hand.toString();
    }

    public int calculateSumOfHand(Hand hand) {
        return HandUtils.calculateSumOfHand(hand);
    }

    public boolean canSplitHand(Hand hand) {
        if (playerHandList.size() < BlackjackConstants.DEFAULT_PLAYER_MAX_NUMBER_OF_HANDS) {
            return hand.isSplittable();
        }
        // TODO need something to let external source (cli, ui) know default limit maxed
        return false;
    }

    public Hand splitHand(Hand hand) {
        if (canSplitHand(hand)) {
            Hand newHand = new Hand();
            newHand.addCard(hand.removeCard(1));
            addHand(newHand);
            return newHand;
        }
        // TODO throw and catch error
        return null;
    }

    public Hand getCurrentHand() {
        return playerHandList.get(currentHandIndex);
    }

    private void setCurrentHand(int index) {
        if (index >= 0 && index < playerHandList.size()) {
            currentHandIndex = index;
        }
    }

    public void incrementCurrentHand() {
        currentHandIndex += 1;
    }

    public boolean isCurrentHand(Hand hand) {
        return currentHandIndex == playerHandList.indexOf(hand);
    }

    public List<Hand> getPlayerHandList() {
        return playerHandList;
    }

    public boolean hasNextHand() {
        return currentHandIndex < playerHandList.size() - 1;
    }



}
