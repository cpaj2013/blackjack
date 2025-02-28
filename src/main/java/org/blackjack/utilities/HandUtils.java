package org.blackjack.utilities;

import org.blackjack.constants.BlackjackConstants;
import org.blackjack.model.Card;
import org.blackjack.model.Hand;

public class HandUtils {
    /**
     * Calculate the sum of an entire hand.
     *
     * @return the sum of the hand, with the correct aces total as well.
     */
    public static int calculateSumOfHand(Hand hand) {
        int sum = 0;
        for (Card c : hand.getHand()) {
            if (c.getRank().getSecondaryValue() == null) {
                sum += c.getRank().getPrimaryValue();
            }
        }

        return calculateSumOfAces(hand, sum);
    }

    /**
     * Calculate the sum included aces.
     *
     * @param runningSum the sum already calculated on non-ace cards.
     * @return calculated sum
     */
    private static int calculateSumOfAces(Hand hand, int runningSum) {
        for (Card c : hand.getHand()) {
            if (c.getRank().getSecondaryValue() != null) {
                int primaryValue = c.getRank().getPrimaryValue();
                if (runningSum + primaryValue <= BlackjackConstants.BUST_LIMIT) {
                    runningSum += primaryValue;
                } else {
                    runningSum += c.getRank().getSecondaryValue();
                }
            }
        }
        return runningSum;
    }

    // TODO create test
    public static boolean checkForBust(Hand hand) {
        int sumOfHand = calculateSumOfHand(hand);
        if (sumOfHand > BlackjackConstants.BUST_LIMIT) {
            System.out.println("Bust. " + (sumOfHand - BlackjackConstants.BUST_LIMIT) + " over.");
            return true;
        }
        return false;
    }
}
