package xyz.gandolfi.aoc20.day18;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day18.txt");
        assert inputLines != null;

        System.out.print("Day 18a: ");
        var sumA = inputLines.stream()
            .map(Expression::parseExpression)
            .map(Expression::getResult)
            .reduce(0L, Long::sum);
        System.out.println(sumA);

        System.out.print("Day 18b: ");
        long sumB = inputLines.stream()
                .map(Expression::parseExpressionWithPlusPrecedence)
                .map(Expression::getResult)
                .reduce(0L, Long::sum);
        System.out.println(sumB);
    }
}
