package xyz.gandolfi.aoc20.day22;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Player {
    private final int playerId;
    private LinkedList<Integer> cards;

    public Player(int playerId, List<Integer> cards) {
        this.playerId = playerId;
        this.cards = new LinkedList<>(cards);
    }

    public Integer getTopCard() {
        return cards.removeFirst();
    }

    public List<Integer> getCards() {
        return cards;
    }

    public void addCardsAtTheBottom(List<Integer> cardsToAdd) {
        for (Integer cardToAdd : cardsToAdd)
            cards.addLast(cardToAdd);
    }

    public boolean hasLost() {
        return cards.isEmpty();
    }

    public int getFinalScore() {
        int result = 0;
        for (int i = 0; i < cards.size(); ++i)
            result += (cards.size() - i) * cards.get(i);
        return result;
    }

    public String getCardsAsString() {
        return cards.toString();
    }

    public int getPlayerId() {
        return playerId;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", cards=" + cards +
                '}';
    }
}
