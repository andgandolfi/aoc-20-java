package xyz.gandolfi.aoc20.day23;

import xyz.gandolfi.aoc20.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day23.txt");
        assert inputLines != null;

        List<Integer> inputNums = Arrays.stream(inputLines.get(0).split(""))
            .map(Integer::parseInt)
            .toList();

        List<Integer> inputNums1M = new ArrayList<>(1_000_000);
        inputNums1M.addAll(inputNums);
        int addFrom = inputNums.stream().max(Integer::compareTo).get() + 1;
        for (int i = addFrom; i <= 1_000_000; ++i)
            inputNums1M.add(i);

        System.out.print("Day 23a: ");
        GameOptimized gameA = new GameOptimized(inputNums);
        gameA.evolveNSteps(100);
        System.out.println(gameA.getResultA());

        System.out.print("Day 23b: ");
        GameOptimized gameB = new GameOptimized(inputNums1M);
        gameB.evolveNSteps(10_000_000);
        System.out.println(gameB.getResultB());
    }
}
