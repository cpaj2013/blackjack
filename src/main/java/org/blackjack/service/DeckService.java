package org.blackjack.service;

import lombok.Getter;
import org.blackjack.constants.BlackjackConstants;
import org.blackjack.enums.Rank;
import org.blackjack.enums.Suit;
import org.blackjack.model.Card;
import org.blackjack.model.Deck;
import org.springframework.stereotype.Service;

@Getter
@Service
public class DeckService {

    private Deck deck;

    DeckService() {
        this.deck = new Deck();
    }

    private void populateDeck() {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(rank, suit);
                deck.getDrawPile().push(card);
            }
        }
    }

    public void initializeDeck() {
        this.deck = new Deck();
    }

    public Deck populateAndShuffleDeck() {
        return populateAndShuffleDeckByMultiplier(BlackjackConstants.DEFAULT_DECK_MULTIPLIER);
    }

    // future logic to handle multiple decks
    public Deck populateAndShuffleDeckByMultiplier(int multiplier) {
        // for each rank, for each suit
        for (int i = 0; i < multiplier; i++) {
            populateDeck();
        }

        deck.shuffle();

        return deck;
    }

    public Card drawCard() {
        return deck.drawCard();
    }

}
