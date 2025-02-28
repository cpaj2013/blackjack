package org.blackjack.model;

import lombok.Getter;

import java.util.Collections;
import java.util.Stack;

@Getter
public class Deck {

    private Stack<Card> drawPile;

    public Deck() {
        drawPile = new Stack<>();
    }

    public void shuffle() {
        Collections.shuffle(drawPile);
    }

    public Card drawCard() {
        return drawPile.pop();
    }

    public int size() {
       return drawPile.size();
    }

}
