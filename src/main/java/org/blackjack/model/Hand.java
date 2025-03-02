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

    public Card removeCard(Card card) {
        cardList.remove(card);
        return card;
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
}
