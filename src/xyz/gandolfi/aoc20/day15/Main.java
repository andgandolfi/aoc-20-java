package xyz.gandolfi.aoc20.day15;

import xyz.gandolfi.aoc20.Utils;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day15.txt");
        assert inputLines != null;

        List<Integer> inputNums = Arrays.stream(inputLines.get(0).split(","))
            .map(Integer::parseInt)
            .toList();

        NumbersProcessor processor;

        System.out.print("Day 15a: ");
        processor = new NumbersProcessor(inputNums);
        System.out.println(processor.getNumberAt(2020));

        System.out.print("Day 15b: ");
        processor = new NumbersProcessor(inputNums);
        System.out.println(processor.getNumberAt(30_000_000));
    }
}
