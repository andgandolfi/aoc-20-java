package xyz.gandolfi.aoc20.day13;

import xyz.gandolfi.aoc20.Utils;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day13.txt");
        assert inputLines != null;

        int earliestTimestamp = Integer.parseInt(inputLines.get(0));
        List<Integer> busses = Arrays.stream(inputLines.get(1).split(","))
            .map(s -> {
                try {
                    return Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    return null;
                }
            })
            .toList();
        TimetableCalculator calculator = new TimetableCalculator(busses, earliestTimestamp);

        System.out.print("Day 13a: ");
        System.out.println(calculator.findEarliestBusAfterTimestamp());

        System.out.print("Day 13b: ");
        System.out.println(calculator.findFirstTimeAllAlign());
    }
}
