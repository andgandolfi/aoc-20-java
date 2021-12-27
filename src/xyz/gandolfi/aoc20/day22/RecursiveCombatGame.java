package xyz.gandolfi.aoc20.day22;

import java.util.*;

public class RecursiveCombatGame {
    private Player player1;
    private Player player2;
    private Set<String> previousGames;

    private static Map<String, Integer> memoized;

    static {
        memoized = new HashMap<>();
    }

    public RecursiveCombatGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        previousGames = new HashSet<>();
    }

    public Player playTillEnd() {
        while (!player1.hasLost() && !player2.hasLost()) {
            String gameId = getGameId();
            if (previousGames.contains(gameId)) {
                return player1;
            }
            previousGames.add(gameId);

            int card1 = player1.getTopCard();
            int card2 = player2.getTopCard();

            if (player1.getCards().size() >= card1 && player2.getCards().size() >= card2) {
                RecursiveCombatGame game = new RecursiveCombatGame(
                    new Player(1, player1.getCards().subList(0, card1)),
                    new Player(2, player2.getCards().subList(0, card2))
                );
                String innerGameId = game.getGameId();
                Integer cachedWinner = memoized.get(innerGameId);
                int winnerPlayerId;
                if (cachedWinner != null)
                    winnerPlayerId = cachedWinner;
                else {
                    Player winner = game.playTillEnd();
                    winnerPlayerId = winner.getPlayerId();
                    memoized.put(innerGameId, winnerPlayerId);
                }

                if (winnerPlayerId == 1)
                    player1.addCardsAtTheBottom(List.of(card1, card2));
                else
                    player2.addCardsAtTheBottom(List.of(card2, card1));
            } else if (card1 > card2)
                player1.addCardsAtTheBottom(List.of(card1, card2));
            else
                player2.addCardsAtTheBottom(List.of(card2, card1));
        }
        return player1.hasLost() ? player2 : player1;
    }

    private String getGameId() {
        return player1.getCardsAsString() + "-" + player2.getCardsAsString();
    }
}
