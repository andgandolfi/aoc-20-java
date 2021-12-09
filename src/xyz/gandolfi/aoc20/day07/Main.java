package xyz.gandolfi.aoc20.day07;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day07.txt");
        assert inputLines != null;

        Bags bags = new Bags(inputLines);

        System.out.print("Day 07a: ");
        System.out.println(bags.countBagsContaining("shiny gold"));

        System.out.print("Day 07b: ");
        System.out.println(bags.countBagsNestedIn("shiny gold"));
    }
}
