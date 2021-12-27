package xyz.gandolfi.aoc20.day22;

import java.util.List;

public class RegularCombatGame {
    private Player player1;
    private Player player2;

    public RegularCombatGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player playTillEnd() {
        while (!player1.hasLost() && !player2.hasLost()) {
            int card1 = player1.getTopCard();
            int card2 = player2.getTopCard();
            if (card1 > card2)
                player1.addCardsAtTheBottom(List.of(card1, card2));
            else
                player2.addCardsAtTheBottom(List.of(card2, card1));
        }
        return player1.hasLost() ? player2 : player1;
    }
}
