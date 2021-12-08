package xyz.gandolfi.aoc20.day03;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day03.txt");
        assert inputLines != null;

        Forest forest = new Forest(inputLines);
        System.out.print("Day 03a: ");
        int x31 = forest.crossForest(0, 0, 3, 1);
        System.out.println(x31);

        System.out.print("Day 03b: ");
        int x11 = forest.crossForest(0, 0, 1, 1);
        int x51 = forest.crossForest(0, 0, 5, 1);
        int x71 = forest.crossForest(0, 0, 7, 1);
        int x12 = forest.crossForest(0, 0, 1, 2);
        System.out.println((long) x11 * x31 * x51 * x71 * x12);
    }
}
