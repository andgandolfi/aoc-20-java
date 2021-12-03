package xyz.gandolfi.aoc20.day01;

import xyz.gandolfi.aoc20.Utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static int findTwoThatSumUpTo(List<Integer> inputNums, int sum) {
        Set<Integer> seen = new HashSet<>();
        for (int num: inputNums) {
            int diff = sum - num;
            if (seen.contains(diff)) return num * diff;
            seen.add(num);
        }
        return -1;
    }

    public static int findThreeThatSumUpTo(List<Integer> inputNums, int sum) {
        return -1;
    }

    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day01.txt");
        assert inputLines != null;
        List<Integer> inputNums = inputLines.stream().map(Integer::parseInt).toList();

        System.out.print("Day 01a: ");
        System.out.println(findTwoThatSumUpTo(inputNums, 2020));

        System.out.print("Day 01b: ");
        System.out.println(findThreeThatSumUpTo(inputNums, 2020));
    }
}
