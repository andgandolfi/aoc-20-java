package xyz.gandolfi.aoc20.day01;

import xyz.gandolfi.aoc20.Utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static int findTwoThatSumUpTo(List<Integer> inputNums, int start, int end, int sum) {
        Set<Integer> seen = new HashSet<>();
        for (int i = start; i <= end; i++) {
            int num = inputNums.get(i);
            int diff = sum - num;
            if (seen.contains(diff)) return num * diff;
            seen.add(num);
        }
        return -1;
    }

    public static int findTwoThatSumUpTo(List<Integer> inputNums, int sum) {
        return findTwoThatSumUpTo(inputNums, 0, inputNums.size() - 1, sum);
    }

    public static int findThreeThatSumUpTo(List<Integer> inputNums, int sum) {
        for (int i = 0; i < inputNums.size(); i++) {
            int firstNum = inputNums.get(i);
            int otherTwo = findTwoThatSumUpTo(inputNums, i + 1, inputNums.size() - 1, sum - firstNum);
            if (otherTwo >= 0) return firstNum * otherTwo;
        }
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
