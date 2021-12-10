package xyz.gandolfi.aoc20.day09;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day09.txt");
        assert inputLines != null;

        List<Long> inputNums = inputLines.stream().map(Long::parseLong).toList();

        System.out.print("Day 09a: ");
        Long errorValue = Checker.findErrorValue(inputNums, 25);
        assert errorValue != null;
        System.out.println(errorValue);

        System.out.print("Day 09b: ");
        List<Long> range = Checker.findRangeAddsUpTo(inputNums, errorValue);
        long[] minMax = Checker.getMinMaxFrom(range);
        System.out.println(minMax[0] + minMax[1]);
    }
}
