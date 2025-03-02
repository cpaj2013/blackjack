package org.blackjack.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeckServiceTest {

    @Test
    public void testPopulateAndShuffleDeck() {
        DeckService deckService = new DeckService();
        deckService.populateAndShuffleDeck();

        Assertions.assertEquals(52, deckService.getDeck().size());
    }

    @Test
    public void testPopulateAndShuffleDeckByMultiplier() {
        DeckService deckService = new DeckService();
        deckService.populateAndShuffleDeck(3);

        Assertions.assertEquals(156, deckService.getDeck().size());
    }

}
