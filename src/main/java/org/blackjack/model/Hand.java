package org.blackjack.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Hand {

    private ArrayList<Card> cardList;

    public Hand() {
        cardList = new ArrayList<>();
    }

    public void addCard(Card card) {
        cardList.add(card);
    }

    public boolean removeCard(Card card) {
        return cardList.remove(card);
    }

    public Card removeCard(int index) {
        return cardList.remove(index);
    }

    public Card getCard(int index) {
        return cardList.get(index);
    }

    public int size() {
        return cardList.size();
    }

    public String toString() {
        String handAsString = " | ";
        for (Card c : cardList) {
            handAsString += c.toString() + " | ";
        }
        return handAsString;
    }

    public boolean isEmpty() {
        return cardList.isEmpty();
    }

    public void flipAllCardsFaceUp() {
        for (Card c : cardList) {
            if (!c.isFaceUp()) {
                c.flipCard();
            }
        }
    }

    public boolean isSplittable() {
        if (cardList.size() != 2) {
            // TODO log why not splittable
            return false;
        }
        return getCard(0).equals(getCard(1));
    }

}
