package xyz.gandolfi.aoc20.day10;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day10.txt");
        assert inputLines != null;

        List<Integer> inputNums = inputLines.stream().map(Integer::parseInt).toList();

        System.out.print("Day 10a: ");
        int[] differences = JoltageCalculator.getAdapterDifferences(inputNums);
        System.out.println(differences[0] * differences[2]);

        System.out.print("Day 10b: ");
        System.out.println(JoltageCalculator.getNumberOfDifferentArrangements(inputNums));
    }
}
