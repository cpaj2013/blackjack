package org.blackjack.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Player {

    private List<Hand> playerHands;

    public Player() {
        this.playerHands = new ArrayList<>();
    }
}
