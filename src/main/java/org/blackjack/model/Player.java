package org.blackjack.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    private Hand hand;

    public Player() {
        this.hand = new Hand();
    }
}
