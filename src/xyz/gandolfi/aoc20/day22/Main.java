package xyz.gandolfi.aoc20.day22;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day22.txt");
        assert inputLines != null;

        List<Integer> player1Cards = inputLines.stream()
            .skip(1)
            .takeWhile(x -> !x.isBlank())
            .map(Integer::parseInt)
            .toList();

        List<Integer> player2Cards = inputLines.stream()
            .dropWhile(x -> !x.isBlank())
            .skip(2)
            .map(Integer::parseInt)
            .toList();

        System.out.print("Day 22a: ");
        RegularCombatGame gameA = new RegularCombatGame(
            new Player(1, player1Cards),
            new Player(2, player2Cards)
        );
        Player winnerA = gameA.playTillEnd();
        System.out.println(winnerA.getFinalScore());

        System.out.print("Day 22b: ");
        RecursiveCombatGame gameB = new RecursiveCombatGame(
            new Player(1, player1Cards),
            new Player(2, player2Cards)
        );
        Player winnerB = gameB.playTillEnd();
        System.out.println(winnerB.getFinalScore());
    }
}
