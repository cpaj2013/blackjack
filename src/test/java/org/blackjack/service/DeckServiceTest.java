package org.blackjack.service;

import org.blackjack.model.Deck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeckServiceTest {

    @Test
    public void testPopulateAndShuffleDeck() {
        DeckService deckService = new DeckService();
        deckService.populateAndShuffleDeck();

        Assertions.assertEquals(52, deckService.getDeck().size());
    }

}
